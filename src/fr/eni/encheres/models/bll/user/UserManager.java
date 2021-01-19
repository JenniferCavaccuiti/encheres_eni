package fr.eni.encheres.models.bll.user;

public class UserManager {

	//---------- Mise en place du Singleton
	
	private static UserManager instance = null;
	
	private UserManager() {}
	
	public static UserManager getInstance() {
		
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	
	
}
