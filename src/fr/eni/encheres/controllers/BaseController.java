package fr.eni.encheres.controllers;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ManagerFactory;
import fr.eni.encheres.models.bo.Category;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class BaseController {

    public static boolean isConnected(Object idUser) {
        return idUser != null;
    }

    public static List<Category> getCategoriesList() throws SQLException, BusinessException {
        return ManagerFactory.getCategoryManager().findAll();
    }

    public static boolean isAfterStartBid(LocalDateTime now, LocalDateTime startBid) {
        return startBid.isAfter(now);
    }
}
