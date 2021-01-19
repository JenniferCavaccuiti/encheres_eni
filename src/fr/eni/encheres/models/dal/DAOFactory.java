package fr.eni.encheres.models.dal;

import fr.eni.encheres.models.dal.item.ItemDAO;
import fr.eni.encheres.models.dal.item.ItemDAOJdbcImpl;

public class DAOFactory {

    public static ItemDAO getAvisDAO()
    {
        return new ItemDAOJdbcImpl();
    }

}
