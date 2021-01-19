package fr.eni.encheres.models.dal.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.ConnectionProvider;
import fr.eni.encheres.models.dal.ResultCodesDAL;

public class UserDAOJdbcImpl implements UserDAO {

	
	//---------- Mise en place du Singleton
	
	private static UserDAO instance = null;
	
	private UserDAOJdbcImpl() { }
	
	public static UserDAO getInstance() {
		
		if(instance == null) {
			instance = new UserDAOJdbcImpl();
		}
		
		return instance;
	}
	
	
	//----------- Méthode d'insertion d'un user en BDD
	
	private static final String insertUser = "INSERT INTO USER (login, lastname, firstname, email, phone_number, street, postal_code, city, password, credits, administrator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
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
			
			if(rs.next()) {
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