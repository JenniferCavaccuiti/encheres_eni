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

	public User connectUser(String login, String password ) throws Exception {
		User user = new User();
		try {
			user = DAOFactory.getUserDAO().selectUserByLogin(login);

			if ( user == null ) {
				throw new Exception("Login inconnu.");
			} 
			// test password
			System.out.println(password);
			if ( !password.equals(user.getPassword())) {
				throw new Exception( "Mot de passe incorrecte." );
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* test print user */
		System.out.println(user.getLogin()+user.getPassword());

		return user;
	}

	/**
	 * Valider l'identifiant saisi.
	 * v1
	 * 	private void validationLogin( String login ) throws Exception {
		if ( login != null && !login.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)") ) {
			throw new Exception( "Merci de saisir une adresse mail valide." );
		}
	}
	 */

	/**
	private void validationLogin( String login ) throws Exception {
		if (login != null && !login.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
			throw new Exception( "Merci de saisir un login valide." );
		}
	}
	 */

	/**
	 * Valider le mot de passe saisi.
	 */
	private void validationPassword( String password ) throws Exception {
		if ( password != null ) {
			if ( password.length() < 3 ) {
				throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
			}
		} else {
			throw new Exception( "Merci de saisir votre mot de passe." );
		}
	}

	/*
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setError( String champ, String message ) {
		errors.put( champ, message );
	}

	/*
	 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
	 * sinon.
	 */
	private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
		String valeur = request.getParameter( nomChamp );
		if ( valeur == null || valeur.trim().length() == 0 ) {
			return null;
		} else {
			return valeur;
		}
	}

}
