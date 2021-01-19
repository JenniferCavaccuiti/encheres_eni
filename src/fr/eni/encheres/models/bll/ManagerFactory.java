package fr.eni.encheres.models.bll;

import fr.eni.encheres.models.bll.user.UserManager;

public abstract class ManagerFactory {

//    Exemple
//  public static AchatManager getAchatManager() {
//      return AchatManager.getInstance();
//  

	public static UserManager getUserManager() {
		return UserManager.getInstance();
	}
}
