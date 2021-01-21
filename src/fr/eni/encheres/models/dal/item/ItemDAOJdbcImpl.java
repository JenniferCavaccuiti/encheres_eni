package fr.eni.encheres.models.dal.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOJdbcImpl implements ItemDAO {

    private static final String SQL_SELECT_ALL_NOT_FINISH = "select * from ITEM WHERE bids_end_date > getdate() ";
    private static final String SQL_SELECT_UNFINISHED_WITH_USER_PROPOSAL = "SELECT * FROM ITEM" +
            " WHERE item_id IN (select item_id FROM BID where buyer_id = ?)" +
            "AND bids_end_date > getdate() AND ITEM.seller_id != ?";

    private static final String SQL_SELECT_UNFINISHED_WITHOUT_USER_PROPOSAL = "SELECT * FROM ITEM" +
            " WHERE item_id NOT IN (select item_id FROM BID where buyer_id = ?)" +
            " AND bids_end_date > getdate() AND ITEM.seller_id != ?";

    private static final String SQL_SELECT_FINISHED_WIN_BY_USER = "SELECT * FROM ITEM JOIN" +
              "(SELECT item_id, max(bid_amount) as max FROM BID WHERE buyer_id = ? GROUP by item_id) b" +
                " on ITEM.item_id = B.item_id AND ITEM.current_price = b.max " +
                "WHERE bids_end_date < getdate()";


    @Override
    public List<Item> findAll() {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL_NOT_FINISH);

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

    public List<Item> findByDateAndBuyerWhithProposal(int idBuyer) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_UNFINISHED_WITH_USER_PROPOSAL);

            pStmt.setInt(1, idBuyer);
            pStmt.setInt(2, idBuyer);

            ResultSet resultSet = pStmt.executeQuery();

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    public List<Item> findByDateAndBuyerWhithoutProposal(int idBuyer) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_UNFINISHED_WITHOUT_USER_PROPOSAL);

            pStmt.setInt(1, idBuyer);
            pStmt.setInt(2, idBuyer);

            ResultSet resultSet = pStmt.executeQuery();

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    public List<Item> findFinishedBidsWinByUser(int idBuyer) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_FINISHED_WIN_BY_USER);

            pStmt.setInt(1, idBuyer);

            ResultSet resultSet = pStmt.executeQuery();

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
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }
}