package fr.eni.encheres.models.dal.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOJdbcImpl implements ItemDAO {

    private static final String SQL_SELECT_ALL_ITEMS =
            "select * from ITEM";

    private static final String SQL_SELECT_ALL_ITEMS_ONGOING =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp ";

    private static final String SQL_SELECT_UNFINISHED_WITH_USER_PROPOSAL =
            "SELECT * FROM ITEM i " +
            "JOIN BID b ON i.item_id = b.item_id " +
            "WHERE bids_end_date > current_timestamp " +
            "AND buyer_id = ?";

    private static final String SQL_ITEM_UNFINISHED_WITHOUT_USER_PROPOSAL =
            "SELECT distinct i.* FROM ITEM i " +
                    "LEFT JOIN BID b ON i.item_id = b.item_id " +
                    "WHERE (b.buyer_id != ? OR b.bid_id IS null) " +
                    "AND i.bids_end_date > current_timestamp " +
                    "AND i.seller_id != ?";

    private static final String SQL_ITEM_WIN_BY_USER =
            "SELECT * FROM ITEM JOIN" +
              "(SELECT item_id, max(bid_amount) as max " +
                    "FROM BID " +
                    "WHERE buyer_id = ? GROUP by item_id) b " +
              "on ITEM.item_id = B.item_id " +
              "AND ITEM.current_price = b.max " +
            "WHERE bids_end_date < getdate()";

    private static final String SQL_ITEM_USER_UNOPENED_SELL =
            "SELECT * FROM ITEM" +
            " JOIN USERS ON USERS.user_id = ITEM.seller_id" +
                    " WHERE bids_start_date > getdate() " +
                    "AND USERS.user_id = ?";

    private static final String SQL_ITEM_USER_SELL_OPENED =
            "SELECT * FROM ITEM" +
            " JOIN USERS ON USERS.user_id = ITEM.seller_id " +
                    "WHERE bids_start_date < getdate() " +
                    "AND bids_end_date > getdate() " +
                    "AND USERS.user_id = ?";

    private static final String SQL_ITEM_USER_FINISHED_SELL =
            "SELECT * FROM ITEM" +
                    " JOIN USERS ON USERS.user_id = ITEM.seller_id" +
                    " WHERE bids_end_date < getdate() " +
                    "AND USERS.user_id = ?";


    private void getResultSetTreatment(List<Item> itemsList, ResultSet resultSet) throws SQLException {
        Item item;
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
    }

    private List<Item> getItems(String sqlSelectAllItemsOngoing) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sqlSelectAllItemsOngoing);

            getResultSetTreatment(itemsList, resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.addError(ResultCodesDAL.SELECT_ALL_ITEMS_FAILED);
        }
        return itemsList;
    }

    private List<Item> getItemsWithUserId(int user, String sqlItemUserSellOpened) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(sqlItemUserSellOpened);

            pStmt.setInt(1, user);

            ResultSet resultSet = pStmt.executeQuery();
            getResultSetTreatment(itemsList, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    public List<Item> findByDateAndBuyerWhithoutProposal(int user) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_ITEM_UNFINISHED_WITHOUT_USER_PROPOSAL);

            pStmt.setInt(1, user);
            pStmt.setInt(2, user);

            ResultSet resultSet = pStmt.executeQuery();
            getResultSetTreatment(itemsList, resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    public List<Item> findFinishedBidsWinByUser(int user) {
        return getItemsWithUserId(user, SQL_ITEM_WIN_BY_USER);
    }

    public List<Item> findNotOpenedBidSellByUser(int user) {
        return getItemsWithUserId(user, SQL_ITEM_USER_UNOPENED_SELL);
    }

    public List<Item> findOpenedSellItemsByUser(int user) {
        return getItemsWithUserId(user, SQL_ITEM_USER_SELL_OPENED);
    }
    public List<Item> findFinishedSellItemsByUser(int user) {
        return getItemsWithUserId(user, SQL_ITEM_USER_FINISHED_SELL);
    }

    public List<Item> findByDateAndBuyerWhithProposal(int user) {
        return getItemsWithUserId(user, SQL_SELECT_UNFINISHED_WITH_USER_PROPOSAL);
    }

    @Override
    public List<Item> findAll() {
        return getItems(SQL_SELECT_ALL_ITEMS);
    }

    public List<Item> findOnGoingItems() {
        return getItems(SQL_SELECT_ALL_ITEMS_ONGOING);
    }
}