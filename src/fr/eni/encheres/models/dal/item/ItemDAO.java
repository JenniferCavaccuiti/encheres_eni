package fr.eni.encheres.models.dal.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    public List<Item> findAll() throws BusinessException, SQLException;

    List<Item> findByDateAndBuyerWhithoutProposal(int idBuyer);

    List<Item> findByDateAndBuyerWhithProposal(int idBuyer);

    List<Item> findFinishedBidsWinByUser(int idBuyer);
}
