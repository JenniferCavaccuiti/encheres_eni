package fr.eni.encheres.models.bll.bid;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Bid;
import fr.eni.encheres.models.dal.DAOFactory;

import java.sql.SQLException;
import java.util.List;

// TODO une bid ne peut être ajoutée si sa date est inf à la date de fin et sup à la date début
// TODO vérifier que la nouvelle bid est supérieure à la dernière faite ou au prix initial

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

//    public List<Bid> sortingBidsByDate(List<Bid> bidsList) {
//        List<Bid> sortedList = null;
//        bid recent;
//        // trier la liste du plus récent au plus ancien
//        // car la plus récente offre sera nécessairement l'enchère la plus haute
//        for (Bid bid : bidsList) {
//
//        }
//    }

}
