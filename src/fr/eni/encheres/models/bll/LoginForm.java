package fr.eni.encheres.models.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.DAOFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class LoginForm {
	private static final String CHAMP_LOGIN  = "login";
	private static final String CHAMP_PASS   = "password";
	private String result;
	private Map<String, String> errors = new HashMap<String, String>();


	public String getResult() {
		return result;
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public User connectUser(String login, String password ) throws BusinessException {
		BusinessException exception = new BusinessException();
		User user = null;
		
		try {
			user = DAOFactory.getUserDAO().selectUserByLogin(login);

			if ( user == null ) {
				exception.addError(ResultCodesBLL.ERROR_CNX_LOGIN);
			} 
			// test password
			System.out.println(password);
			if ( !password.equals(user.getPassword())) {
				exception.addError(ResultCodesBLL.ERROR_CNX_PASSWORD);
			}
			if ( exception.hasErreurs() ) {
				throw exception;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	private void validationPassword( String password ) throws Exception {
		if ( password != null ) {
			if ( password.length() < 3 ) {
				throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
			}
		} else {
			throw new Exception( "Merci de saisir votre mot de passe." );
		}
	}

	private void setError( String champ, String message ) {
		errors.put( champ, message );
	}


	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur;
		}
	}

}
