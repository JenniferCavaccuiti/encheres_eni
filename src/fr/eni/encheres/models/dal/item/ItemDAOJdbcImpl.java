package fr.eni.encheres.models.dal.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOJdbcImpl implements ItemDAO {

    //------- Requête Select pour la barre de recherche -------------//

    private static final String SQL_SELECT_ALL_ITEMS =
            "select * from ITEM";

    private static final String SQL_SELECT_ALL_ITEMS_ONGOING =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp ";

    private static final String SQL_SELECT_ALL_ITEMS_ONGOING_BY_CATEGORY =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp " +
                    "AND category_id = ?";

    private static final String SQL_SELECT_ALL_ITEMS_BY_CATEGORY =
            "select * from ITEM " +
                    "WHERE category_id = ?";


    private static final String SQL_SELECT_ALL_ITEMS_ONGOING_BY_KEYWORD =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp " +
                    "AND item_name LIKE ?";

    private static final String SQL_SELECT_ALL_ITEMS_BY_KEYWORD =
            "select * from ITEM " +
                    "WHERE item_name LIKE ?";

    private static final String SQL_SELECT_ALL_ITEMS_ONGOING_BY_CATEGORY_AND_KEYWORD =
            "select * from ITEM " +
                    "WHERE bids_end_date > current_timestamp " +
                    "AND item_name LIKE ? " +
                    "AND category_id = ?";

    private static final String SQL_SELECT_ALL_ITEMS_BY_CATEGORY_AND_KEYWORD =
            "select * from ITEM " +
                    "WHERE item_name LIKE ? " +
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
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_ALL_ITEMS_ONGOING_BY_KEYWORD);

            pStmt.setString(1, "%" + key + "%");

            setResultset(itemsList, pStmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    public List<Item> findAllItemsByKeyword(String key) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_ALL_ITEMS_BY_KEYWORD);

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

    @Override
    public List<Item> findAllItemsByKeywordAndCategory(String key, int category) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_ALL_ITEMS_BY_CATEGORY_AND_KEYWORD);

            pStmt.setString(1, "%" + key + "%");
            pStmt.setInt(2, category);

            setResultset(itemsList, pStmt);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return itemsList;
    }

    public List<Item> findAllItemsByCategory(int category) {
        List<Item> itemsList = new ArrayList<>();
        Item item;

        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_SELECT_ALL_ITEMS_BY_CATEGORY);
            pStmt.setInt(1, category);

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

    //--------------- Requête insertion d'une nouvelle vente -------------//
    private static final String SQL_INSERT_ITEM =
            "INSERT INTO ITEM " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public Item insertNewItem(Item item) {
        try (Connection connection = ConnectionProvider.getConnection()) {
            PreparedStatement pStmt = connection.prepareStatement(SQL_INSERT_ITEM, PreparedStatement.RETURN_GENERATED_KEYS);

            pStmt.setString(1, item.getItemName());
            pStmt.setString(2, item.getDescription());
            pStmt.setTimestamp(3, Timestamp.valueOf(item.getBidsStartDate()));
            pStmt.setTimestamp(4, Timestamp.valueOf(item.getBidsEndDate()));
            pStmt.setInt(5, item.getInitialPrice());
            pStmt.setInt(6, item.getCurrentPrice());
            pStmt.setInt(7, item.getIdSeller());
            pStmt.setString(8, item.getStreet());
            pStmt.setString(9, item.getPostalCode());
            pStmt.setString(10, item.getCity());
            pStmt.setInt(11, item.getIdCategory());

            pStmt.executeUpdate();

            ResultSet rs = pStmt.getGeneratedKeys();

            if (rs.next()) {
                item.setIdItem(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }


    //------------- Méthode qui selectionne un item via son id

    private static final String selectItemById = "SELECT * FROM ITEM where item_id = ?";

    @Override
    public Item selectItemById(int id) throws BusinessException {

    	Item item = null;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectItemById);) {

			pStmt.setInt(1, id);

			ResultSet resultSet = pStmt.executeQuery();

			if (resultSet.next()) {

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
			}

			resultSet.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.addError(ResultCodesDAL.SELECT_ITEM_ID_FAILED);
			throw exception;
		}

    	return item;
    }
    
    //---------------- Méthode de mise à jour d'un item
    
    private static final String updateItemById = "UPDATE ITEM SET item_name = ?, description = ?, bids_start_date = ?, bids_end_date = ?, initial_price = ?, current_price = ?, seller_id = ?, street = ?, postal_code = ?, city = ?, category_id = ? WHERE item_id = ?";
	
	@Override
	public Item updateItem(Item item) throws BusinessException {
		
		BusinessException exception = new BusinessException();

		if (item == null) {
			exception.addError(ResultCodesDAL.INSERT_OBJET_NULL);
			throw exception;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(updateItemById)) {

			pStmt.setString(1, item.getItemName());
			pStmt.setString(2, item.getDescription());
			pStmt.setTimestamp(3, Timestamp.valueOf(item.getBidsStartDate()));
			pStmt.setTimestamp(4, Timestamp.valueOf(item.getBidsEndDate()));
			pStmt.setInt(5, item.getInitialPrice());
			pStmt.setInt(6, item.getCurrentPrice());
			pStmt.setInt(7, item.getIdSeller());
			pStmt.setString(8, item.getStreet());
			pStmt.setString(9, item.getPostalCode());
			pStmt.setString(10, item.getCity());
			pStmt.setInt(11, item.getIdCategory());
			pStmt.setInt(12, item.getIdItem());

			pStmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			exception.addError(ResultCodesDAL.INSERT_UPDATE_OBJET_FAILED);
			throw exception;
		}
		
		return item;
		
	}

}