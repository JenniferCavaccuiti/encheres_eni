package fr.eni.encheres.models.dal.item;

import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.ConnectionProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOJdbcImpl implements ItemDAO {

    private static final String SQL_SELECT_ALL_ITEMS =
            "select * from ITEM";

    private static final String SQL_SELECT_ALL_ITEMS_ONGOING =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp ";

    private static final String SQL_SELECT_ALL_ITEMS_ONGOING_BY_CATEGORY =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp " +
                    "AND category_id = ?";

    private static final String SQL_SELECT_ALL_ITEMS_ONGOING_By_KEYWORD =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp " +
                    "AND item_name LIKE ?";

    private static final String SQL_SELECT_ALL_ITEMS_ONGOING_BY_CATEGORY_AND_KEYWORD =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp " +
                    "AND item_name LIKE ? " +
                    "AND category_id = ?";

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

    private List<Item> getItemsStatement(String sqlItemUserSellOpened, int option, int option2) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(sqlItemUserSellOpened);

            if (option != 0) {
                pStmt.setInt(1, option);
            }
            if (option2 != 0) {
                pStmt.setInt(1, option);
                pStmt.setInt(2, option2);
            }

            setResultset(itemsList, pStmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    public List<Item> findByDateAndBuyerWhithoutProposal(int user) {
        return getItemsStatement(SQL_ITEM_UNFINISHED_WITHOUT_USER_PROPOSAL, user, user);
    }

    public List<Item> findFinishedBidsWinByUser(int user) {
        return getItemsStatement(SQL_ITEM_WIN_BY_USER, user, 0);
    }

    public List<Item> findNotOpenedBidSellByUser(int user) {
        return getItemsStatement(SQL_ITEM_USER_UNOPENED_SELL, user, 0);
    }

    public List<Item> findOpenedSellItemsByUser(int user) {
        return getItemsStatement(SQL_ITEM_USER_SELL_OPENED, user, 0);
    }

    public List<Item> findFinishedSellItemsByUser(int user) {
        return getItemsStatement(SQL_ITEM_USER_FINISHED_SELL, user, 0);
    }

    public List<Item> findByDateAndBuyerWhithProposal(int user) {
        return getItemsStatement(SQL_SELECT_UNFINISHED_WITH_USER_PROPOSAL, user, 0);
    }

    @Override
    public List<Item> findAll() {
        return getItemsStatement(SQL_SELECT_ALL_ITEMS, 0, 0);
    }

    public List<Item> findOnGoingItems() {
        return getItemsStatement(SQL_SELECT_ALL_ITEMS_ONGOING, 0, 0);
    }

    public List<Item> findOnGoingItemsByCategory(int category) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_ALL_ITEMS_ONGOING_BY_CATEGORY);
            pStmt.setInt(1, category);

            setResultset(itemsList, pStmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    public List<Item> findOnGoingItemsByKeyword(String key) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_ALL_ITEMS_ONGOING_By_KEYWORD);

            pStmt.setString(1, "%" + key + "%");

            setResultset(itemsList, pStmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    @Override
    public List<Item> findOnGoingItemsByKeywordAndCategory(String key, int category) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_ALL_ITEMS_ONGOING_BY_CATEGORY_AND_KEYWORD);

            pStmt.setString(1, "%" + key + "%");
            pStmt.setInt(2, category);

            setResultset(itemsList, pStmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    private void setResultset(List<Item> itemsList, PreparedStatement pStmt) throws SQLException {
        Item item;
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
    }
}