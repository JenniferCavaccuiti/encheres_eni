package fr.eni.encheres.models.dal.user;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;

public interface UserDAO {

	public void insertUser (User user) throws BusinessException;
	
	public List<User> selectLogin (String login) throws BusinessException;
	
	public List<User> selectEmail(String email) throws BusinessException;
	
}
