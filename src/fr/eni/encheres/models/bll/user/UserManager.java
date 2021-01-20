package fr.eni.encheres.models.bll.user;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ResultCodesBLL;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.DAOFactory;
import fr.eni.encheres.models.dal.user.UserDAO;

public class UserManager {

	UserDAO userDAO = DAOFactory.getUserDAO();
	
	// ---------- Mise en place du Singleton

	private static UserManager instance = null;

	private UserManager() {
	}

	public static UserManager getInstance() {

		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
		

	// -------- Méthode pour vérifier que le pseudo est bien composé de caractères
	// alphanumérique && sans accent

	public void loginIsAlphanum(String login, BusinessException exception) {

		if (!login.matches("[a-zA-Z0-9]")) {
			exception.addError(ResultCodesBLL.ERROR_PSEUDO);
		}
	}

	// -------- Méthode qui renvoie une liste de logins si le login passé en param
	// est déjà en BDD

	public List<User> selectSimilarLogin(String login) throws BusinessException {
		return DAOFactory.getUserDAO().selectLogin(login);
	}

	// -------- Méthode pour vérifier que le login n'existe pas

	public void loginExists(String login, BusinessException exception) throws BusinessException {

		List<User> loginList = selectSimilarLogin(login);
		if (!(loginList == null)) {
			exception.addError(ResultCodesBLL.ERROR_PSEUDO_EXISTS);
		}
	}

	// -------- Méthode qui renvoie une liste d'emails si l'email passé en param est
	// déjà en BDD

	public List<User> selectSimilarEmail(String email) throws BusinessException {
		return DAOFactory.getUserDAO().selectEmail(email);
	}

	// -------- Méthode pour vérifier que l'email n'existe pas

	public void emailExists(String email, BusinessException exception) throws BusinessException {

		List<User> emailList = selectSimilarEmail(email);
		if (!(emailList == null)) {
			exception.addError(ResultCodesBLL.ERROR_EMAIL_EXISTS);
		}
	}

	// --------- Méthode d'insertion d'un user en BDD après verif du pseudo et de
	// l'email

	public User addUser (String login, String lastname, String firstname, String email, String phoneNumber, String street, String postalCode, String city, String password, boolean administrator) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		this.loginIsAlphanum(login, exception);
		this.selectSimilarLogin(login);
		this.loginExists(login, exception);
		this.selectSimilarEmail(email);
		this.emailExists(email, exception);
		
		User user = null;
		
		if(!exception.hasErreurs()) {
			
			user = new User();
			user.setLogin(login);
			user.setLastname(lastname);
			user.setFirstname(firstname);
			user.setEmail(email);
			user.setPhoneNumber(phoneNumber);
			user.setStreet(street);
			user.setPostalCode(postalCode);
			user.setCity(city);
			user.setPassword(password);
			user.setAdministrator(administrator);
			user.setCredits(0);
			
			this.userDAO.insertUser(user);
		}
		
		else {
			throw exception;
		}
		
		return user;
	}

	public List<User> findAll() throws BusinessException {
		return DAOFactory.getUserDAO().findAll();
	}
	
}
