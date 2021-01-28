package fr.eni.encheres.models.bll.bid;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ResultCodesBLL;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.bo.Item;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class BidManager {

    private static BidManager instance = null;

    public static BidManager getInstance() {
        if(instance == null) {
            instance = new BidManager();
        }
        return instance;
    }

    public List<Bid> findAll() throws BusinessException, SQLException {
        return DAOFactory.getBidDAO().findAll();
    }
    
    //-------------- Méthode qui renvoit le login du plus gros enchérisseur sur un article
    
    public String biggestBuyer(Item item) throws BusinessException {
    	    	
    	int id = DAOFactory.getBidDAO().biggestBider(item);
    	return DAOFactory.getUserDAO().findOneById(id);
    }
    
    //------------- Méthode qui vérifie si un enréchisseur peut enréchir
    
    public boolean bidIsPossible(User user, Item item, int bidderPrice, BusinessException exception) throws BusinessException {
		
    	boolean isPossible;
    		
    	if((user.getCredits() - bidderPrice) < 0) {
    		exception.addError(ResultCodesBLL.ERROR_MISSING_CREDITS);
    	}
    	
    	if(!exception.hasErrors()) {
    		isPossible = true;
    	}
    	else {
    		isPossible = false;
    		throw exception;
    	}
    		
    	return isPossible;
    	
    }
    
    //------------- Méthode d'insertion de l'enchère 
    
    public Bid insertBid(Bid bid) throws BusinessException {
    	return DAOFactory.getBidDAO().insertBid(bid);
    }
    
    //--------------- Méthode de suppresion d'enchère pour un utilisateur
    
    public void deleteBidByIdBuyer(User user) throws BusinessException {
    	DAOFactory.getBidDAO().deleteBidByIdUser(user);
    }
    
//--------------- Méthode de suppresion d'enchère pour un article
    
    public void deleteBidByItem(Item item) throws BusinessException {
    	DAOFactory.getBidDAO().deleteBidByItem(item);
    }
    
    
}
