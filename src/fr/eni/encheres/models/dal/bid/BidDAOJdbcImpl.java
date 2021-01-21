package fr.eni.encheres.models.dal.bid;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

import java.sql.Connection;
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

}
