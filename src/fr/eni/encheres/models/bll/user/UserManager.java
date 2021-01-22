package fr.eni.encheres.models.bll.user;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ResultCodesBLL;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.DAOFactory;
import fr.eni.encheres.models.dal.user.UserDAO;

public class UserManager {

	private UserDAO userDAO;

	public UserManager() {
		this.userDAO = DAOFactory.getUserDAO();
	}

	// ---------- Mise en place du Singleton

	 //private static UserManager instance = null;

	// private UserManager() {
	// }

	//public static UserManager getInstance() {

	//	if (instance == null) {
	//	instance = new UserManager();
	//}
	//return instance;
	//}

	// --------- Méthode de mise à jour d'un user en BDD après verif du pseudo, de
	// l'email, de la correspondance entre le nouveau mdp et la confirmation
	// et de la correspondance de l'ancien mdp en BDD
	
	
	
	// --------- Méthode d'insertion d'un user en BDD après verif du pseudo et de
	// l'email, et de la correspondance entre le mdp et la confirmation
	
	public void addUser(String login, String lastname, String firstname, String email, String phoneNumber,
			String street, String postalCode, String city, String password, String confirmPassword, boolean administrator) throws BusinessException
			 {

		BusinessException exception = new BusinessException();
			
			confirmPassword(password, confirmPassword, exception);
			loginIsAlphanum(login, exception);
			loginExists(login, exception);
			emailExists(email, exception);
		
		User user = null;

		if (!exception.hasErreurs()) {

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
			user.setCredits(100);

			userDAO.insertUser(user);
		}
		
		else {
			throw exception;
		}

	}

	// -------- Méthode pour vérifier que le pseudo est bien composé de
	// caractères
	// alphanumérique && sans accent

	public void loginIsAlphanum(String login, BusinessException exception) {

		if (!(login.matches("[a-zA-Z0-9]+"))) {
			exception.addError(ResultCodesBLL.ERROR_PSEUDO);
		}
	}

	// -------- Méthode pour vérifier que le login n'existe pas en BDD

	public void loginExists(String login, BusinessException exception) throws BusinessException {

		String log = DAOFactory.getUserDAO().selectLogin(login);
		if (!(log.isEmpty())) {
			exception.addError(ResultCodesBLL.ERROR_PSEUDO_EXISTS);
		}
	}

	
	// -------- Méthode confirm mot de passe
	
		public void confirmPassword (String password, String confirmPassword, BusinessException exception) { 
			
			if(!password.equals(confirmPassword)) {
				exception.addError(ResultCodesBLL.ERROR_CONFIRM_PASSWORD);
			}
			
		}

	// -------- Méthode pour vérifier que l'email n'existe pas en BDD

	public void emailExists(String email, BusinessException exception) throws BusinessException {

		String mail = DAOFactory.getUserDAO().selectEmail(email);
		if (!(mail.isEmpty())) {
			exception.addError(ResultCodesBLL.ERROR_EMAIL_EXISTS);
		}
	}

	//---------- Méthode pour sélectionner un profil utilisateur en BDD via son login
	
	public User selectUserByLog(String login) throws BusinessException {
		return DAOFactory.getUserDAO().selectUserByLogin(login);
	}
	
	// -------- Méthode qui retourne la liste des utilisateurs
	
		public List<User> findAll() throws BusinessException {
			return DAOFactory.getUserDAO().findAll();
		}
		
}
