package fr.eni.encheres.models.dal.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

public class UserDAOJdbcImpl implements UserDAO {

	
	// ---------- Mise en place du Singleton

	private static UserDAO instance = null;

	private UserDAOJdbcImpl() {
	}

	public static UserDAO getInstance() {

		if (instance == null) {
			instance = new UserDAOJdbcImpl();
		}

		return instance;
	}

	
	
	// ----------- Méthode qui sélectionne en BDD le login correspondant à la valeur
	// ------------ passée en param (null si le login n'est pas présent en base)

	private static final String selectLogin = "SELECT login FROM USERS WHERE login = ?";

	@Override
	public List<User> selectLogin(String login) throws BusinessException {
		
		List<User> loginList = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectLogin);) {
			
			pStmt.setString(1, login);
			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				
				User user = new User(rs.getString("login"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("email"), rs.getString("phone_number"), rs.getString("street"), rs.getString("postal_code"), rs.getString("city"), rs.getString("password"), rs.getInt("credits"), rs.getBoolean("administrator")); 
				loginList.add(user);
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.addError(ResultCodesDAL.SELECT_LOGIN_ECHEC);
			throw exception;
		}
		
		return loginList;

	}
	
	
	
	//------------ Méthode qui sélectionne en BDD l'email correspondant à la valeur
	// ------------ passée en param (null si l'email n'est pas présent en base)

	private static final String selectEmail = "SELECT email FROM USERS WHERE email = ?";

	@Override
	public List<User> selectEmail(String email) throws BusinessException {
		
		List<User> emailList = new ArrayList<>();
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectEmail);) {
			
			pStmt.setString(1, email);
			
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				
				User user = new User(rs.getString("login"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("email"), rs.getString("phone_number"), rs.getString("street"), rs.getString("postal_code"), rs.getString("city"), rs.getString("password"), rs.getInt("credits"), rs.getBoolean("administrator")); 
				emailList.add(user);
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.addError(ResultCodesDAL.SELECT_EMAIL_ECHEC);
			throw exception;
		}
		
		return emailList;

	}
	
	
	
	// ----------- Méthode d'insertion d'un user en BDD

	private static final String insertUser = "INSERT INTO USERS (login, lastname, firstname, email, phone_number, street, postal_code, city, password, credits, administrator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	@Override
	public void insertUser(User user) throws BusinessException {

		BusinessException exception = new BusinessException();

		if (user == null) {
			exception.addError(ResultCodesDAL.INSERT_OBJET_NULL);
			throw exception;
		}

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(insertUser, PreparedStatement.RETURN_GENERATED_KEYS)) {

			pStmt.setString(1, user.getLogin());
			pStmt.setString(2, user.getLastname());
			pStmt.setString(3, user.getFirstname());
			pStmt.setString(4, user.getEmail());
			pStmt.setString(5, user.getPhoneNumber());
			pStmt.setString(6, user.getStreet());
			pStmt.setString(7, user.getPostalCode());
			pStmt.setString(8, user.getCity());
			pStmt.setString(9, user.getPassword());
			pStmt.setInt(10, user.getCredits());
			pStmt.setBoolean(11, user.isAdministrator());

			pStmt.executeUpdate();

			ResultSet rs = pStmt.getGeneratedKeys();

			if (rs.next()) {
				user.setIdUser(rs.getInt(1));
			}

			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
			exception.addError(ResultCodesDAL.INSERT_OBJET_ECHEC);
			throw exception;
		}

	}

}