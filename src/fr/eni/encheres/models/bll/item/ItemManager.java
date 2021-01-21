package fr.eni.encheres.models.bll.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    private static ItemManager instance = null;

    public static ItemManager getInstance() {
        if(instance == null) {
            instance = new ItemManager();
        }
        return instance;
    }

    public List<Item> findAll() throws BusinessException, SQLException {
       return DAOFactory.getItemDAO().findAll();
    }

    public List<Item> searchedItems(String searchedWord, List<Item> itemsList) throws BusinessException {
        List<Item> searchedItemsList = new ArrayList<>();
        for (Item item : itemsList) {
            if (item.getItemName().contains(searchedWord)) {
                searchedItemsList.add(item);
            }
        }
        return searchedItemsList;
    }
}
