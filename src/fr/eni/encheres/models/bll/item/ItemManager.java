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

    public List<Item> findAll() throws BusinessException, SQLException {
        return DAOFactory.getItemDAO().findAll();
    }

    public List<Item> findByDateAndBuyerWhithoutProposal(int idBuyer) throws BusinessException, SQLException {
        return DAOFactory.getItemDAO().findByDateAndBuyerWhithoutProposal(idBuyer);
    }

    public List<Item> findByDateAndBuyerWhithProposal(int idBuyer) throws BusinessException, SQLException {
        return DAOFactory.getItemDAO().findByDateAndBuyerWhithProposal(idBuyer);
    }

    public List<Item> findFinishedBidsWinByUser(int idBuyer) throws BusinessException, SQLException {
        return DAOFactory.getItemDAO().findFinishedBidsWinByUser(idBuyer);
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
    case "openedBuy" :
        newItemsList = ManagerFactory.getItemManager().findByDateAndBuyerWhithoutProposal(user);
        break;
    case "onGoingBuy" :
        newItemsList = ManagerFactory.getItemManager().findByDateAndBuyerWhithProposal(user);
        break;
    case "wonBuy" :
        newItemsList = ManagerFactory.getItemManager().findFinishedBidsWinByUser(user);
        break;
    case "openedSell" :
        break;
    case "onGoingSell" :
        break;
    case "wonSell" :
        break;
}

        return newItemsList;
    }

}
