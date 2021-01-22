package fr.eni.encheres.models.dal.user;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;

public interface UserDAO {

	public void insertUser (User user) throws BusinessException;
	
	public String selectLogin (String login) throws BusinessException;
	
	public String selectEmail(String email) throws BusinessException;

	public List<User> findAll() throws BusinessException;
	
	public User selectUserByLogin(String login) throws BusinessException;
	
	public String selectPasswordById(int id) throws BusinessException;
	
	public void updateUserById(User user) throws BusinessException;

}
