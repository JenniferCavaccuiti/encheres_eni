package fr.eni.encheres.models.dal.category;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDAO {

    List<Category> findAll() throws BusinessException, SQLException;

    String findOneById(int idCategory);
}
