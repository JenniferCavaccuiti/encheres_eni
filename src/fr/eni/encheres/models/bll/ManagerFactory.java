package fr.eni.encheres.models.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.models.bll.bid.BidManager;
import fr.eni.encheres.models.bll.item.ItemManager;
import fr.eni.encheres.models.bll.category.CategoryManager;
import fr.eni.encheres.models.bll.user.UserManager;

public abstract class ManagerFactory {

    public static ItemManager getItemManager() {
        return ItemManager.getInstance();
    }

	public static UserManager getUserManager() {
		return new UserManager();
	}

    public static CategoryManager getCategoryManager() {
        return new CategoryManager();
    }

    public static BidManager getBidManager() {
        return new BidManager();
    }

    public static LoginForm getLoginForm(BusinessException exceptionList) {
        return new LoginForm(exceptionList);
    }
}

