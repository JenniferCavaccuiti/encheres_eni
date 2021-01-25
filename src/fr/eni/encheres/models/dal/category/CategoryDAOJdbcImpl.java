package fr.eni.encheres.models.dal.category;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Category;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOJdbcImpl implements CategoryDAO {

    private static final String SQL_SELECT_ALL = "select * from CATEGORY";
    private static final String SQL_SELECT_ONE = "select wording from CATEGORY WHERE category_id = ?";

    @Override
    public List<Category> findAll() throws BusinessException, SQLException {
        List<Category> categoriesList = new ArrayList<>();
        Category category;

        try (Connection connection = ConnectionProvider.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                category = new Category();
                category.setIdCategory(resultSet.getInt("category_id"));
                category.setWording(resultSet.getString("wording"));

                categoriesList.add(category);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.addError(ResultCodesDAL.SELECT_ALL_CATEGORIES_FAILED);
        }
        return categoriesList;
    }

    @Override
    public String findOne(int idCategory) {
        String categoryName = null;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_ONE);

            pStmt.setInt(1, idCategory);
            ResultSet resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
            categoryName = resultSet.getString("wording");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryName;
    }
}

