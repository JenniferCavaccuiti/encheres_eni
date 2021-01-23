package fr.eni.encheres.models.dal.item;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {

    List<Item> findAll() throws BusinessException, SQLException;
}
