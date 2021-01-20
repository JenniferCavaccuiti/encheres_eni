package fr.eni.encheres.models.bll;

import fr.eni.encheres.models.bll.item.ItemManager;

import fr.eni.encheres.models.bll.user.UserManager;

public abstract class ManagerFactory {

    public static ItemManager getItemManager() {
        return ItemManager.getInstance();
    }

	//public static UserManager getUserManager() {
	//	return UserManager.getInstance();
	//}

}
