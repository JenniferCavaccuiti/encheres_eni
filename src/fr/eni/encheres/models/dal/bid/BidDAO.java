package fr.eni.encheres.models.dal.bid;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.bo.Item;

import java.sql.SQLException;
import java.util.List;

public interface BidDAO {

    List<Bid> findAll() throws BusinessException, SQLException;
    
    public int biggestBider(Item item) throws BusinessException;

}
