package fr.eni.encheres.models.bll.category;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Category;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class CategoryManager {

    private static CategoryManager instance = null;

    public static CategoryManager getInstance() {
        if(instance == null) {
            instance = new CategoryManager();
        }
        return instance;
    }

    public List<Category> findAll() throws BusinessException, SQLException {
        return DAOFactory.getCategoryDAO().findAll();
    }

    public String findOneById(int idCategory) {
        return DAOFactory.getCategoryDAO().findOneById(idCategory);
    }
}
