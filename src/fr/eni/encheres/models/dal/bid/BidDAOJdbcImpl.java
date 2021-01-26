package fr.eni.encheres.models.dal.bid;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BidDAOJdbcImpl implements BidDAO {

    private static final String SQL_SELECT_ALL = "select * from BID";


    @Override
    public List<Bid> findAll() throws BusinessException, SQLException {
        List<Bid> bidsList = new ArrayList<>();
        Bid bid;

        try (Connection connection = ConnectionProvider.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(SQL_SELECT_ALL);

            while (resultSet.next()) {
                bid = new Bid();
                bid.setIdBid(resultSet.getInt("bid_id"));
                bid.setIdBuyer(resultSet.getInt("buyer_id"));
                bid.setIdItem(resultSet.getInt("item_id"));
                bid.setBidDate(resultSet.getTimestamp("bid_date").toLocalDateTime());
                bid.setBidAmount(resultSet.getInt("bid_amount"));

                bidsList.add(bid);
            }
        } catch (Exception e) {
            e.printStackTrace();
            BusinessException businessException = new BusinessException();
            businessException.addError(ResultCodesDAL.SELECT_ALL_CATEGORIES_FAILED);
        }
        return bidsList;
    }
    
    
//----------------- Méthode qui retourne l'id de l'user qui a fait la plus grosse enchère en fonction de l'id de l'item
    
    private static final String selectIdSellerBiggestBid = "select buyer_id from ITEM as I inner join BID as B on I.item_id = B.item_id WHERE bid_amount = (select MAX(bid_amount) from BID WHERE item_id = ?)";
    
    @Override
    public int biggestBider(Item item) throws BusinessException {
    	
    	BusinessException exception = new BusinessException();
		int id = 0;
    	
		if (item == null) {
			exception.addError(ResultCodesDAL.INSERT_OBJET_NULL);
			throw exception;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectIdSellerBiggestBid)) {

			pStmt.setInt(1, item.getIdItem());
			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {
				id = rs.getInt(1);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			exception.addError(ResultCodesDAL.SELECT_BUYERID_FAILED);
			throw exception;
		}
    	
    	return id;
    }


}
