package fr.eni.encheres.models.bll.user;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.ResultCodesBLL;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.DAOFactory;
import fr.eni.encheres.models.dal.ResultCodesDAL;
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

	public void updateUser(User user, String login, String lastname, String firstname, String email, String phoneNumber,
			String street, String postalCode, String city, String oldPassword, String newPassword, String confirmPassword) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		passwordExists(user.getPassword(), oldPassword, exception);
		
		if (!login.isEmpty() && !user.getLogin().equals(login)) {
			
			loginIsAlphanum(login, exception);
			loginExists(login, exception);
			
			if(!exception.hasErrors()) {
				user.setLogin(login);
			}
		}
		
		if(!firstname.isEmpty() && !user.getFirstname().equals(firstname)) {
			user.setFirstname(firstname);
		}
		
		if(!lastname.isEmpty() && !user.getLastname().equals(lastname)) {
			user.setLastname(lastname);
		}
		
		if(!email.isEmpty() && !user.getEmail().equals(email)) {
			
			emailExists(email, exception);
			
			if(!exception.hasErrors()) {
				user.setEmail(email);
			}
		}
		
		if(!phoneNumber.isEmpty() && !user.getPhoneNumber().equals(phoneNumber)) {
			user.setPhoneNumber(phoneNumber);
		}
		
		if(!street.isEmpty() && !user.getStreet().equals(street)) {
			user.setStreet(street);
		}
		
		if(!postalCode.isEmpty() && !user.getPostalCode().equals(postalCode)) {
			user.setPostalCode(postalCode);
		}
		
		if(!city.isEmpty() && !user.getCity().equals(city)) {
			user.setCity(city);
		}
		
		if(!newPassword.isEmpty()) {
			confirmPassword(newPassword, confirmPassword, exception);
			if(!exception.hasErrors()) {
				user.setPassword(newPassword);
			}
		}
		
				
		if (!exception.hasErrors()) {

			userDAO.updateUserById(user);
		}
		
		else {
			throw exception;
		}

	}

	// --------- Méthode d'insertion d'un user en BDD après verif du pseudo et de
		// l'email
	
	public void addUser(String login, String lastname, String firstname, String email, String phoneNumber,
			String street, String postalCode, String city, String password, String confirmPassword, boolean administrator) throws BusinessException
			 {

		BusinessException exception = new BusinessException();
			
			confirmPassword(password, confirmPassword, exception);
			loginIsAlphanum(login, exception);
			loginExists(login, exception);
			emailExists(email, exception);
		
		User user = null;

		if (!exception.hasErrors()) {

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
	
	// 
	
	public void passwordExists(String userPassword, String oldPassword, BusinessException exception) throws BusinessException {
		
		if(!userPassword.equals(oldPassword)) {
			exception.addError(ResultCodesBLL.ERROR_OLD_PASSWORD);
		}		
	}

	// -------- Méthode pour vérifier que le login n'existe pas en BDD

	public void loginExists(String login, BusinessException exception) throws BusinessException {

		List<String> logList = DAOFactory.getUserDAO().selectLogin(login);
		if (!(logList.isEmpty())) {
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

		List<String> mailList = DAOFactory.getUserDAO().selectEmail(email);
		if (!(mailList.isEmpty())) {
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
