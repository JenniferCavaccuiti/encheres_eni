package fr.eni.encheres.models.dal.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

public class UserDAOJdbcImpl implements UserDAO {

	// ---------- Mise en place du Singleton

	private static UserDAO instance = null;

	public UserDAOJdbcImpl() {
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
	public String selectLogin(String login) throws BusinessException {

		String log = null;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectLogin);) {

			pStmt.setString(1, login);

			ResultSet rs = pStmt.executeQuery();

			if(rs.next()) {

				log = rs.getString(1);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.addError(ResultCodesDAL.SELECT_LOGIN_FAILED);
			throw exception;
		}

		return log;

	}

	
	// ------------ Méthode qui renvoie le mot de passe pour l'user dont l'id est en paramètres
	
	private static final String selectPasswordById = "SELECT password FROM USERS WHERE user_id = ?";
	
	@Override
	public String selectPasswordById(int id) throws BusinessException {

		String password = null;
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectPasswordById);) {

			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				password = rs.getString(1);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.addError(ResultCodesDAL.SELECT_PASSWORD_FAILED);
			throw exception;
		}
		
		return password;
	}

	// ------------ Méthode qui sélectionne en BDD l'email correspondant à la valeur
	// ------------ passée en param (null si l'email n'est pas présent en base)

	private static final String selectEmail = "SELECT email FROM USERS WHERE email = ?";

	@Override
	public String selectEmail(String email) throws BusinessException {

		String mail = null;

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectEmail);) {

			pStmt.setString(1, email);

			ResultSet rs = pStmt.executeQuery();

			if (rs.next()) {
				mail = rs.getString(1);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.addError(ResultCodesDAL.SELECT_EMAIL_FAILED);
			throw exception;
		}

		return mail;

	}
	
	// ------------ Méthode qui sélectionne en BDD l'utilisateur correspondant à la valeur
	// ------------ passée en param (null si le login n'est pas présent en base)

		private static final String selectUserByLogin = "SELECT * FROM USERS WHERE login = ?";

		@Override
		public User selectUserByLogin(String login) throws BusinessException {
			
			User user = null;
			
			try (Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement pStmt = cnx.prepareStatement(selectUserByLogin);) {
				
				pStmt.setString(1, login);
			
				ResultSet rs = pStmt.executeQuery();
			
				if (rs.next()) {
					
					user = new User(rs.getInt("user_id"), rs.getString("login"), rs.getString("lastname"),
							rs.getString("firstname"), 	rs.getString("email"), rs.getString("phone_number"), 
							rs.getString("street"), rs.getString("postal_code"), rs.getString("city"), 
							rs.getString("password"), rs.getInt("credits"), rs.getBoolean("administrator"));
				}

				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
				BusinessException exception = new BusinessException();
				exception.addError(ResultCodesDAL.SELECT_USER_FAILED);
				throw exception;
			}
			
			return user;
		}
		
		
		// ------------------ Méthode qui sert à lister l'ensemble des users

		private static final String SELECT_ALL_LOGIN = "SELECT * FROM USERS";

		public List<User> findAll() throws BusinessException {
			List<User> usersList = new ArrayList<>();
			User user;

			try (Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement pStmt = cnx.prepareStatement(SELECT_ALL_LOGIN)) {

				ResultSet rs = pStmt.executeQuery();

				while (rs.next()) {

					user = new User(rs.getInt("user_id"), rs.getString("login"), rs.getString("lastname"),
							rs.getString("firstname"), rs.getString("email"), rs.getString("phone_number"),
							rs.getString("street"), rs.getString("postal_code"), rs.getString("city"),
							rs.getString("password"), rs.getInt("credits"), rs.getBoolean("administrator"));
					usersList.add(user);
				}

				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return usersList;
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

		} catch (SQLException e) {
			e.printStackTrace();
			exception.addError(ResultCodesDAL.INSERT_UPDATE_OBJET_FAILED);
			throw exception;
		}

	}
	
	//------------------ Méthode de modification d'un user (cherché en BDD via son id)

	private static final String updateUserById = "UPDATE USERS SET login = ?, lastname = ?, firstname = ?, email = ?, phone_number = ?, street = ?, postal_code = ?, city = ?, password = ? WHERE login = ?";
	
	@Override
	public void updateUserById(User user) throws BusinessException {
		
		BusinessException exception = new BusinessException();

		if (user == null) {
			exception.addError(ResultCodesDAL.INSERT_OBJET_NULL);
			throw exception;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(updateUserById)) {

			pStmt.setString(1, user.getLogin());
			pStmt.setString(2, user.getLastname());
			pStmt.setString(3, user.getFirstname());
			pStmt.setString(4, user.getEmail());
			pStmt.setString(5, user.getPhoneNumber());
			pStmt.setString(6, user.getStreet());
			pStmt.setString(7, user.getPostalCode());
			pStmt.setString(8, user.getCity());
			pStmt.setString(9, user.getPassword());
			pStmt.setInt(10, user.getIdUser());
			

			pStmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			exception.addError(ResultCodesDAL.INSERT_UPDATE_OBJET_FAILED);
			throw exception;
		}
		
		
	}

	

}