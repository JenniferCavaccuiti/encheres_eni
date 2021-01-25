package fr.eni.encheres.models.bll;

import java.util.Scanner;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.DAOFactory;

//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;

public class LoginForm {
//	private static final String CHAMP_LOGIN  = "login";
//	private static final String CHAMP_PASS   = "password";
//	private String result;
//	private Map<String, String> errors = new HashMap<String, String>();

    private BusinessException exceptionList;

    public LoginForm(BusinessException exceptionList) {
        this.exceptionList = exceptionList;
    }

    public User connectUser(String login, String password) throws BusinessException {
        User user = null;

        // j'essaye de récupérer l'user en base
        try {
        	if( login.contains("@") ) {
        		user = (DAOFactory.getUserDAO().selectUserByEmail(login));
        	} else {
        		user = (DAOFactory.getUserDAO().selectUserByLogin(login));
        	}
        } catch (Exception e) {
            e.printStackTrace();
            this.exceptionList.addError(ResultCodesBLL.ERROR_CNX_LOGIN);
        }

        if (user == null) {
            this.exceptionList.addError(ResultCodesBLL.ERROR_CNX_LOGIN);
            return null;
        }

        System.out.println(password);
        if (!password.equals(user.getPassword())) {
            this.exceptionList.addError(ResultCodesBLL.ERROR_CNX_PASSWORD);
        }
        return user;
    }

//    private void validationPassword(String password) throws Exception {
//        if (password != null) {
//            if (password.length() < 3) {
//                throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
//            }
//        } else {
//            throw new Exception("Merci de saisir votre mot de passe.");
//        }
//    }
//
//    private void setError(String champ, String message) {
//        errors.put(champ, message);
//    }
//
//
//    private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
//        String valeur = request.getParameter(nomChamp);
//        if (valeur == null || valeur.trim().length() == 0) {
//            return null;
//        } else {
//            return valeur;
//        }
//    }

}
