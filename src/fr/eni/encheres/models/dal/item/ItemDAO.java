package fr.eni.encheres.models.dal.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    List<Item> findAll() throws BusinessException, SQLException;

    List<Item> findByDateAndBuyerWhithoutProposal(int idBuyer);

    List<Item> findByDateAndBuyerWhithProposal(int idBuyer);

    List<Item> findFinishedBidsWinByUser(int idBuyer);

    List<Item> findNotOpenedBidSellByUser(int user);

    List<Item> findOpenedSellItemsByUser(int user);

    List<Item> findFinishedSellItemsByUser(int user);

    List<Item> findOnGoingItems();

    List<Item> findOnGoingItemsByCategory(int category);

    List<Item> findOnGoingItemsByKeyword(String searchedWord);

    List<Item> findOnGoingItemsByKeywordAndCategory(String searchedWord, int category);

    List<Item> findAllItemsByCategory(int category);

    List<Item> findAllItemsByKeyword(String searchedWord);

    List<Item> findAllItemsByKeywordAndCategory(String searchedWord, int category);

    Item insertNewItem(Item item);

    public Item selectItemById(int id) throws BusinessException;
    
    public Item updateItem(Item item) throws BusinessException;

    }

