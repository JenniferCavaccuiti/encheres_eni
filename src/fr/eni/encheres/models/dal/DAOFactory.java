package fr.eni.encheres.models.dal;

import fr.eni.encheres.models.dal.item.ItemDAO;
import fr.eni.encheres.models.dal.item.ItemDAOJdbcImpl;
import fr.eni.encheres.models.dal.user.UserDAO;
import fr.eni.encheres.models.dal.user.UserDAOJdbcImpl;

public class DAOFactory {

    public static ItemDAO getItemDAO()
    {
        return new ItemDAOJdbcImpl();
    }
    
    public static UserDAO getUserDAO() {
    	return UserDAOJdbcImpl.getInstance();
    }

}
