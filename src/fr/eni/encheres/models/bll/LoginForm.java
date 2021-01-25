package fr.eni.encheres.models.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bo.User;
import fr.eni.encheres.models.dal.DAOFactory;


public class LoginForm {

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

}
