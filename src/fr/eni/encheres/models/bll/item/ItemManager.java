package fr.eni.encheres.models.bll.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.DAOFactory;

import java.sql.SQLException;
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

    public List<Item> findOnGoingItems() throws BusinessException, SQLException {
        return DAOFactory.getItemDAO().findOnGoingItems();
    }

    public List<Item> searchedItems(String searchedWord, List<Item> itemsList, String searchedCategory) throws BusinessException {
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

    public List<Item> searchedItemsByFilter(String filter, int user) throws SQLException, BusinessException {
        List<Item> newItemsList = new ArrayList<>();

        switch (filter) {
            case "openedBuy":
                newItemsList = DAOFactory.getItemDAO().findByDateAndBuyerWhithoutProposal(user);
                break;
            case "onGoingBuy":
                newItemsList = DAOFactory.getItemDAO().findByDateAndBuyerWhithProposal(user);
                break;
            case "wonBuy":
                newItemsList = DAOFactory.getItemDAO().findFinishedBidsWinByUser(user);
                break;
            case "nonOpenedSell":
                newItemsList = DAOFactory.getItemDAO().findNotOpenedBidSellByUser(user);
                break;
            case "onGoingSell":
                newItemsList = DAOFactory.getItemDAO().findOpenedSellItemsByUser(user);
                break;
            case "finishedSell":
                newItemsList = DAOFactory.getItemDAO().findFinishedSellItemsByUser(user);
                break;
        }
        return newItemsList;
    }

}
