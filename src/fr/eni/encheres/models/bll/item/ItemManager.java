package fr.eni.encheres.models.bll.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.DAOFactory;

import java.sql.SQLException;
import java.text.DateFormat;
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
}
