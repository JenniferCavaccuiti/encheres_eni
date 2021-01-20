package fr.eni.encheres.models.dal.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOJdbcImpl implements ItemDAO {

    private static final String SQL_SELECT_ALL = "select * from Item";

    @Override
    public List<Item> findAll() {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL);
            System.out.println("je passe ici");

            while (resultSet.next()) {
                item = new Item();
                item.setIdItem(resultSet.getInt("item_id"));
                item.setItemName(resultSet.getString("item_name"));
                item.setDescription(resultSet.getString("description"));
                item.setBidsStartDate(resultSet.getTimestamp("bids_start_date").toLocalDateTime());
                item.setBidsEndDate(resultSet.getTimestamp("bids_end_date").toLocalDateTime());
                item.setInitialPrice(resultSet.getInt("initial_price"));
                item.setCurrentPrice(resultSet.getInt("current_price"));
                item.setIdSeller(resultSet.getInt("seller_id"));
                item.setStreet(resultSet.getString("street"));
                item.setPostalCode(resultSet.getString("postal_code"));
                item.setCity(resultSet.getString("city"));
                item.setIdCategory(resultSet.getInt("category_id"));

                itemsList.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.addError(ResultCodesDAL.SELECT_ALL_ITEMS_FAILED);
        }
        return itemsList;
    }
}
