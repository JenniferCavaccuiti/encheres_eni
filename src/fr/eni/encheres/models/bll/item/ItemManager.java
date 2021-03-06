package fr.eni.encheres.models.bll.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.BaseManager;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.DAOFactory;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class ItemManager {

    private static ItemManager instance = null;

    public static ItemManager getInstance() {
        if (instance == null) {
            instance = new ItemManager();
        }
        return instance;
    }

    // récupère tous les items et les bids
    public List<Item> findAll() throws BusinessException, SQLException {
        List<Item> itemsList = DAOFactory.getItemDAO().findAll();
        setSellerAndBidsToItemsList(itemsList);
        return itemsList;
    }

    public List<Item> findOnGoingItems() throws BusinessException, SQLException {
        List<Item> itemsList = DAOFactory.getItemDAO().findOnGoingItems();
        setSellerAndBidsToItemsList(itemsList);
        return itemsList;
    }

    private void setSellerAndBidsToItemsList(List<Item> itemsList) throws BusinessException, SQLException {
        List<Bid> bidsList = ManagerFactory.getBidManager().findAll();

        for (Item item : itemsList) {
            item.setCategoryName(DAOFactory.getCategoryDAO().findOneById(item.getIdCategory()));
            item.setSellerName(DAOFactory.getUserDAO().findOneById(item.getIdSeller()));
        }

        for (Item item : itemsList) {
            for (Bid bid : bidsList) {
                if (item.getIdItem() == bid.getIdItem()) {
                    item.addBid(bid);
                }
            }
        }
    }

    public List<Item> getNonConnectedList(String keyword, String searchedCategory) throws BusinessException, SQLException {
        List<Item> itemsList;

        keyword = BaseManager.prepareWord(keyword);
        int category = Integer.parseInt(searchedCategory);

        if (keyword == null && category == 0) {
            itemsList = DAOFactory.getItemDAO().findOnGoingItems();
        } else if (keyword == null && category != 0) {
            itemsList = DAOFactory.getItemDAO().findOnGoingItemsByCategory(category);
        } else if (keyword != null && category == 0) {
            itemsList = DAOFactory.getItemDAO().findOnGoingItemsByKeyword(keyword);
        } else {
            itemsList = DAOFactory.getItemDAO().findOnGoingItemsByKeywordAndCategory(keyword, category);
        }
        setSellerAndBidsToItemsList(itemsList);
        return itemsList;
    }

    public List<Item> getConnectedList(String keyword, String searchedCategory) throws BusinessException, SQLException {
        List<Item> itemsList;

        keyword = BaseManager.prepareWord(keyword);
        int category = Integer.parseInt(searchedCategory);

        if (keyword == null && category == 0) {
            itemsList = DAOFactory.getItemDAO().findAll();
        } else if (keyword == null && category != 0) {
            itemsList = DAOFactory.getItemDAO().findAllItemsByCategory(category);
        } else if (keyword != null && category == 0) {
            itemsList = DAOFactory.getItemDAO().findAllItemsByKeyword(keyword);
        } else {
            itemsList = DAOFactory.getItemDAO().findAllItemsByKeywordAndCategory(keyword, category);
        }
        setSellerAndBidsToItemsList(itemsList);
        return itemsList;
    }


    public List<Item> searchedItemsByFilter(String filter, int user) throws SQLException, BusinessException {
        List<Item> newItemsList = new ArrayList<>();

        switch (filter) {
            case "openedBuy":
                newItemsList = DAOFactory.getItemDAO().findByDateAndBuyerWhithoutProposal(user);
                break;
            case "onGoingBuy":
                newItemsList = DAOFactory.getItemDAO().findByDateAndBuyerWhithProposal(user);
                break;
            case "wonBuy":
                newItemsList = DAOFactory.getItemDAO().findFinishedBidsWinByUser(user);
                break;
            case "nonOpenedSell":
                newItemsList = DAOFactory.getItemDAO().findNotOpenedBidSellByUser(user);
                break;
            case "onGoingSell":
                newItemsList = DAOFactory.getItemDAO().findOpenedSellItemsByUser(user);
                break;
            case "finishedSell":
                newItemsList = DAOFactory.getItemDAO().findFinishedSellItemsByUser(user);
                break;
        }
        setSellerAndBidsToItemsList(newItemsList);
        return newItemsList;
    }

    //--------------- Méthode qui récupère en item en fonction de son id

    public Item getItemById(int id) throws BusinessException {
        return DAOFactory.getItemDAO().selectItemById(id);
    }

    //---------------- Méthode qui set le nom de la catégorie et du vendeur pour un item

    public Item setSellerNameCategoryName(Item item) {
        item.setCategoryName(DAOFactory.getCategoryDAO().findOneById(item.getIdCategory()));
        item.setSellerName(DAOFactory.getUserDAO().findOneById(item.getIdSeller()));

        return item;
    }

    // --------- Ajout d'un nouvel item ------ //


    public Item addItem(Map<String, String[]> parameters, int idUser) {
        Item item = new Item();

        item = getItem(parameters, idUser);

//        // l'ajouter en base et le renvoi
        return DAOFactory.getItemDAO().insertNewItem(item);
    }

    public LocalDateTime getCorrectFormatDate(String date, String time) {
        return LocalDateTime.parse(date + "T" + time + ":00.000");
    }

    //--------------- Méthode d'update d'un item

    public Item updateItemById(Item item) throws BusinessException {
        return DAOFactory.getItemDAO().updateItem(item);
    }
    
    //--------------- Méthode de suppresion d'item pour un utilisateur
    
    public void deleteItemByIdSeller(User user) throws BusinessException {
    	DAOFactory.getItemDAO().deleteItemByIdUser(user);
    }
    
    // ------------- Méthode qui renvoie la liste des articles d'un user
    
    public List<Item> selectItemByUser(User user) throws BusinessException {
    	return DAOFactory.getItemDAO().selectItemByUser(user);
    }

    //TODO commentaires
    
    public Item getItem(Map<String, String[]> parameters, int idUser) {
        Item item = new Item();
        LocalDateTime bidsStartDate = getCorrectFormatDate(parameters.get("bidsStartDate")[0], parameters.get("bidsStartTime")[0]);
        LocalDateTime bidsEndDate = getCorrectFormatDate(parameters.get("bidsEndDate")[0], parameters.get("bidsEndTime")[0]);

//         créer l'item
        item.setItemName(BaseManager.prepareWord(parameters.get("itemName")[0]));
        item.setDescription(BaseManager.prepareWord(parameters.get("description")[0]));
        item.setBidsStartDate(bidsStartDate);
        item.setBidsEndDate(bidsEndDate);
        item.setInitialPrice(Integer.parseInt(parameters.get("initialPrice")[0]));
        item.setCurrentPrice(Integer.parseInt(parameters.get("initialPrice")[0]));
        item.setIdSeller(idUser);
        item.setStreet(BaseManager.prepareWord(parameters.get("street")[0]));
        item.setPostalCode(BaseManager.prepareWord(parameters.get("postalCode")[0]));
        item.setCity(BaseManager.prepareWord(parameters.get("city")[0]));
        item.setIdCategory(Integer.parseInt(parameters.get("category")[0]));

        return item;
    }

    public Item updateItem(Map<String, String[]> parameters, int idUser) throws BusinessException {
        Item item;
        item = getItem(parameters, idUser);
        item.setIdItem(Integer.parseInt(parameters.get("idItem")[0]));
        Item newItem = DAOFactory.getItemDAO().updateItem(item);
        return newItem;
    }

    public int deleteItem(int idItem) {
        int isDelete = DAOFactory.getItemDAO().deleteItem(idItem);
        return isDelete;
    }
}
