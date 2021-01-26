package fr.eni.encheres.models.dal.user;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;

public interface UserDAO {

	public User insertUser (User user) throws BusinessException;
	
	public List<String> selectLogin (String login) throws BusinessException;
	
	public List<String> selectEmail(String email) throws BusinessException;

	public List<User> findAll() throws BusinessException;
	
	public User selectUserByLogin(String login) throws BusinessException;
	
	public User updateUserById(User user) throws BusinessException;
	
	public void deleteUserById(User user) throws BusinessException;

    public String findOneById(int id);
}

