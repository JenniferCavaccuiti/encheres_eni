package fr.eni.encheres.models.bll.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.DAOFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ItemManager {

    private static ItemManager instance = null;

    public static ItemManager getInstance() {
        if (instance == null) {
            instance = new ItemManager();
        }
        return instance;
    }

    // récupère tous les items et les bids
    public List<Item> findAll() throws BusinessException, SQLException {
        List<Item> itemsList = DAOFactory.getItemDAO().findAll();
        List<Bid> bidsList = ManagerFactory.getBidManager().findAll();

        for (Item item : itemsList) {
            for (Bid bid : bidsList) {
                if (item.getIdItem() == bid.getIdItem()) {
                    item.addBid(bid);
                }
            }
        }
        return itemsList;
    }

    public List<Item> searchedItemsByFilter(String filter, int user) throws SQLException, BusinessException {
        List<Item> newItemsList = new ArrayList<>();

        switch (filter) {
            case "openedBuy": //enchères en cours
                newItemsList = findOnGoingBids(user);
                break;
            case "onGoingBuy": // enchères où l'user a fait une enchère
                newItemsList = findOpenedBidsWithUserProposal(user);
                break;
            case "wonBuy": // enchères gagnées
                newItemsList = findFinishedBidsWinByUser(user);
                break;
            case "openedSell": // ventes de l'user non commencées
                newItemsList = findUserBidsNotOpened(user);
                break;
            case "onGoingSell": // ventes en cours
                newItemsList = findUserBidsOpened(user);
                break;
            case "wonSell": // ventes terminées
                newItemsList = findUserBidsFinished(user);
                break;
        }
        return newItemsList;
    }

    public List<Item> findOnGoingBids(int user) throws BusinessException, SQLException {
        List<Item> itemsList = findAll();
        List<Item> filteredList = new ArrayList<>();

        for (Item item : itemsList) {
            if (LocalDateTime.now().compareTo(item.getBidsEndDate()) < 0
                    && item.getIdSeller() != user) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public List<Item> findFinishedBidsWinByUser(int user) throws BusinessException, SQLException {

        // je récupère toutes les enchères
        List<Item> itemsList = findAll();
        List<Item> filteredList = new ArrayList<>();

        // manque l'idée de récupérer la dernière enchère
        for (Item item : itemsList) {
            if (LocalDateTime.now().compareTo(item.getBidsEndDate()) > 0) {
                for (Bid bid : item.getBidsList()) {
                    if (bid.getIdBuyer() == user && item.getCurrentPrice() == bid.getBidAmount()) {
                        filteredList.add(item);
                    }
                }
            }
        }
        return filteredList;
    }

    public List<Item> findOpenedBidsWithUserProposal(int user) throws SQLException, BusinessException {
        List<Item> itemsList = findAll();
        List<Item> filteredList = new ArrayList<>();

        // manque l'idée de récupérer la dernière enchère
        for (Item item : itemsList) {
            if (LocalDateTime.now().compareTo(item.getBidsEndDate()) < 0) {
                for (Bid bid : item.getBidsList()) {
                    if (bid.getIdBuyer() == user && item.getCurrentPrice() == bid.getBidAmount()) {
                        filteredList.add(item);
                    }
                }
            }
        }
        return filteredList;
    }

    public List<Item> findUserBidsNotOpened(int user) throws SQLException, BusinessException {
        List<Item> itemsList = findAll();
        List<Item> filteredList = new ArrayList<>();

        for (Item item : itemsList) {
            if (item.getIdSeller() == user && LocalDateTime.now().compareTo(item.getBidsStartDate()) < 0) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public List<Item> findUserBidsOpened(int user) throws SQLException, BusinessException {
        List<Item> itemsList = findAll();
        List<Item> filteredList = new ArrayList<>();

        for (Item item : itemsList) {
            if (item.getIdSeller() == user
                    && LocalDateTime.now().compareTo(item.getBidsStartDate()) > 0
                    && LocalDateTime.now().compareTo(item.getBidsEndDate()) < 0) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public List<Item> findUserBidsFinished(int user) throws SQLException, BusinessException {
        List<Item> itemsList = findAll();
        List<Item> filteredList = new ArrayList<>();

        for (Item item : itemsList) {
            if (item.getIdSeller() == user
                    && LocalDateTime.now().compareTo(item.getBidsEndDate()) > 0) {
                filteredList.add(item);
            }
        }
        return filteredList;
    }

    public List<Item> searchedItems(String searchedWord, List<Item> itemsList, String searchedCategory) throws
            BusinessException {
//        Mettre mot en minuscule et enlever les espaces avant et après
        searchedWord = searchedWord.toLowerCase(Locale.ROOT);
        searchedWord = searchedWord.trim();

        int category = Integer.parseInt(searchedCategory);

//        Conserver uniquement les items contenant le mot
        List<Item> searchedItemsList = new ArrayList<>();

        if (category == 0) {
            for (Item item : itemsList) {
                if (item.getItemName().contains(searchedWord)) {
                    searchedItemsList.add(item);
                }
            }
        } else {
            for (Item item : itemsList) {
                if (item.getItemName().contains(searchedWord)
                        && (item.getIdCategory() == category)) {
                    searchedItemsList.add(item);
                }
            }
        }
        return searchedItemsList;
    }
}
