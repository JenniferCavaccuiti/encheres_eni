package fr.eni.encheres.models.bll.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Bid;
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
        setSellerAndBidsToItemsList(itemsList);
        return itemsList;
    }

    public List<Item> findOnGoingItems() throws BusinessException, SQLException {
        List<Item> itemsList = DAOFactory.getItemDAO().findOnGoingItems();
        setSellerAndBidsToItemsList(itemsList);
        return itemsList;
    }

    private void setSellerAndBidsToItemsList(List<Item> itemsList) throws BusinessException, SQLException {
        List<Bid> bidsList = ManagerFactory.getBidManager().findAll();

        for (Item item : itemsList) {
            item.setCategoryName(DAOFactory.getCategoryDAO().findOne(item.getIdCategory()));
            item.setSellerName(DAOFactory.getUserDAO().findOneById(item.getIdSeller()));
            System.out.println(item.getSellerName());
        }

        for (Item item : itemsList) {
            for (Bid bid : bidsList) {
                if (item.getIdItem() == bid.getIdItem()) {
                    item.addBid(bid);
                }
            }
        }
    }

    public List<Item> getNonConnectedList(String searchedWord, String searchedCategory) throws BusinessException, SQLException {
        List<Item> itemsList;

        searchedWord = prepareKeyWord(searchedWord);
        int category = Integer.parseInt(searchedCategory);

        if (searchedWord == null && category == 0) {
            itemsList = DAOFactory.getItemDAO().findOnGoingItems();
        } else if (searchedWord == null && category != 0) {
            itemsList = DAOFactory.getItemDAO().findOnGoingItemsByCategory(category);
        } else if (searchedWord != null && category == 0) {
            itemsList = DAOFactory.getItemDAO().findOnGoingItemsByKeyword(searchedWord);
        } else {
            itemsList = DAOFactory.getItemDAO().findOnGoingItemsByKeywordAndCategory(searchedWord, category);
        }
        setSellerAndBidsToItemsList(itemsList);
        return itemsList;
    }

//    public List<Item> connectedSearch(String searchedWord, String searchedCategory, List<Item> itemsList) throws BusinessException, SQLException {
//        List<Item> itemsList = new ArrayList<>();
//
////        Mettre mot en minuscule et enlever les espaces avant et après
//        searchedWord = prepareKeyWord(searchedWord);
//        int category = Integer.parseInt(searchedCategory);
//
//        // TODO rechercher items correspondants
//        // items pas connectés
////
//
//
//        if (category != 0) {
//            searchedItemsList = searchByCategory(category);
//        } else {
//
//        }


//        Conserver uniquement les items contenant le mot


        // TODO searchByCategory

        // findOnGoingItemsByCategory
        // TODO searchBYCAtegORYANdKeyWord

        // TODO searchBYKeyWord


        // si la catégorie vaut 0, je recherche tous les items contenant le mot clé
//        if (category == 0) {
//            for (Item item : itemsList) {
//                if (item.getItemName().contains(searchedWord)) {
//                    searchedItemsList.add(item);
//                }
//            } //si la catégorie est définie, je recherche tous les items contenant le mot clé dans cette catégorie
//        } else {
//            for (Item item : itemsList) {
//                if (item.getItemName().contains(searchedWord)
//                        && (item.getIdCategory() == category)) {
//                    searchedItemsList.add(item);
//                }
//            }
//        }
//        return searchedItemsList;
//    }

    public String prepareKeyWord(String keyWord) {
        keyWord = keyWord.toLowerCase(Locale.ROOT);
        keyWord = keyWord.trim();
        return keyWord;
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
        setSellerAndBidsToItemsList(newItemsList);
        return newItemsList;
    }

}
