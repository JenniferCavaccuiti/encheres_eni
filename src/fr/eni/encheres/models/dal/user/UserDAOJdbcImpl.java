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
	public List<String> selectLogin(String login) throws BusinessException {

		List<String> listLogin = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectLogin);) {

			pStmt.setString(1, login);

			ResultSet rs = pStmt.executeQuery();

			while(rs.next()) {

				String log = rs.getString(1);
				listLogin.add(log);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.addError(ResultCodesDAL.SELECT_LOGIN_FAILED);
			throw exception;
		}

		return listLogin;

	}

	
	// ------------ Méthode qui sélectionne en BDD l'email correspondant à la valeur
	// ------------ passée en param (null si l'email n'est pas présent en base)

	private static final String selectEmail = "SELECT email FROM USERS WHERE email = ?";

	@Override
	public List<String> selectEmail(String email) throws BusinessException {

		List<String> emailList = new ArrayList<>();

		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(selectEmail);) {

			pStmt.setString(1, email);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				String mail = rs.getString(1);
				emailList.add(mail);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException exception = new BusinessException();
			exception.addError(ResultCodesDAL.SELECT_EMAIL_FAILED);
			throw exception;
		}

		return emailList;

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
		
		// ------------ Méthode qui sélectionne en BDD l'utilisateur correspondant à la valeur
		// ------------ passée en param (null si l'email n'est pas présent en base)

			private static final String selectUserByEmail = "SELECT * FROM USERS WHERE email = ?";

			@Override
			public User selectUserByEmail(String email) throws BusinessException {

				User user = null;
				
				try (Connection cnx = ConnectionProvider.getConnection();
						PreparedStatement pStmt = cnx.prepareStatement(selectUserByEmail);) {
					
					pStmt.setString(1, email);
				
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
	public User insertUser(User user) throws BusinessException {

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

		return user;
	}
	
	//------------------ Méthode de modification d'un user (cherché en BDD via son id)

	private static final String updateUserById = "UPDATE USERS SET login = ?, lastname = ?, firstname = ?, email = ?, phone_number = ?, street = ?, postal_code = ?, city = ?, password = ?, credits = ? WHERE user_id = ?";
	
	@Override
	public User updateUserById(User user) throws BusinessException {
		
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
			pStmt.setInt(10, user.getCredits());
			pStmt.setInt(11, user.getIdUser());
			

			pStmt.executeUpdate();


		} catch (SQLException e) {
			e.printStackTrace();
			exception.addError(ResultCodesDAL.INSERT_UPDATE_OBJET_FAILED);
			throw exception;
		}
		
		return user;
		
	}
	
	//-------------- Méthode de suppression d'un user en BDD via son id
	
	private static final String deleteUser = "DELETE FROM USERS where user_id = ?";
	
	@Override
	public void deleteUserById(User user) throws BusinessException {
		
		BusinessException exception = new BusinessException();
		
		if (user == null) {
			exception.addError(ResultCodesDAL.INSERT_OBJET_NULL);
			throw exception;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pStmt = cnx.prepareStatement(deleteUser)) {

			pStmt.setInt(1, user.getIdUser());
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			exception.addError(ResultCodesDAL.DELETE_USER_FAILED);
			throw exception;
		}
		
	}
	
	//--------------- Méthode qui retourne le login de l'utilisateur en fonction de son id

	private static final String findOneById = "SELECT login FROM USERS WHERE user_id = ?";

	public String findOneById(int id) {
		String login = null;

		try (Connection connection = ConnectionProvider.getConnection()) {
			PreparedStatement pStmt = connection.prepareStatement(findOneById);

			pStmt.setInt(1, id);
			ResultSet resultSet = pStmt.executeQuery();

            while (resultSet.next()) {
			login = resultSet.getString("login");
            }
            
            resultSet.close();
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		return login;
	}

	
}