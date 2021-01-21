package fr.eni.encheres.models.dal;

import fr.eni.encheres.models.dal.category.CategoryDAO;
import fr.eni.encheres.models.dal.category.CategoryDAOJdbcImpl;
import fr.eni.encheres.models.dal.item.ItemDAO;
import fr.eni.encheres.models.dal.item.ItemDAOJdbcImpl;
import fr.eni.encheres.models.dal.user.UserDAO;
import fr.eni.encheres.models.dal.user.UserDAOJdbcImpl;

public abstract class DAOFactory {

    public static ItemDAO getItemDAO()
    {
        return new ItemDAOJdbcImpl();
    }
    
    public static UserDAO getUserDAO() {
    	return new UserDAOJdbcImpl();
    }

    public static CategoryDAO getCategoryDAO() {
        return new CategoryDAOJdbcImpl();
    }
}
