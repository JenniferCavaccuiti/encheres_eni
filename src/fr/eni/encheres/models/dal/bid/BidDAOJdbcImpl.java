package fr.eni.encheres.models.dal.bid;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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

    //---------------------- Méthode d'insertion d'une enchère en BDD
    
    private static final String insertBid = "INSERT INTO BID (buyer_id, item_id, bid_date, bid_amount) VALUES (?, ?, ?, ?)";

	@Override
	public Bid insertBid(Bid bid) throws BusinessException {

		BusinessException exception = new BusinessException();

		if (bid == null) {
			exception.addError(ResultCodesDAL.INSERT_OBJET_NULL);
			throw exception;
		}

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(insertBid, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pStmt.setInt(1, bid.getIdBuyer());
			pStmt.setInt(2, bid.getIdItem());
			pStmt.setTimestamp(3, Timestamp.valueOf(bid.getBidDate()));
			pStmt.setInt(4, bid.getBidAmount());
			
			pStmt.executeUpdate();

			ResultSet rs = pStmt.getGeneratedKeys();

			if (rs.next()) {
				bid.setIdBid(rs.getInt(1));
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			exception.addError(ResultCodesDAL.INSERT_UPDATE_OBJET_FAILED);
			throw exception;
		}

		return bid;
	}
	
	
	//----------------- Méthode de suppression de tous les enchères correspondantes à un user en BDD
    
		private static final String deleteBidByIdUser = "DELETE FROM BID where buyer_id = ?";
		
		@Override
		public void deleteBidByIdUser(User user) throws BusinessException {
			
			BusinessException exception = new BusinessException();
			
			if (user == null) {
				exception.addError(ResultCodesDAL.INSERT_OBJET_NULL);
				throw exception;
			}
			
			try (Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement pStmt = cnx.prepareStatement(deleteBidByIdUser)) {

				pStmt.setInt(1, user.getIdUser());
				pStmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
				exception.addError(ResultCodesDAL.DELETE_ITEM_FAILED);
				throw exception;
			}
			
		}
		
	
		//----------------- Méthode de suppression de tous les enchères correspondantes à un article en BDD
	    
			private static final String deleteBidByItem = "DELETE FROM BID where item_id = ?";
			
			@Override
			public void deleteBidByItem(Item item) throws BusinessException {
				
				BusinessException exception = new BusinessException();
				
				if (item == null) {
					exception.addError(ResultCodesDAL.INSERT_OBJET_NULL);
					throw exception;
				}
				
				try (Connection cnx = ConnectionProvider.getConnection();
						PreparedStatement pStmt = cnx.prepareStatement(deleteBidByItem)) {

					pStmt.setInt(1, item.getIdItem());
					pStmt.executeUpdate();

				} catch (SQLException e) {
					e.printStackTrace();
					exception.addError(ResultCodesDAL.DELETE_ITEM_FAILED);
					throw exception;
				}
				
			}


}
