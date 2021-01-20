package fr.eni.encheres.models.bll;

import fr.eni.encheres.models.bll.item.ItemManager;

public abstract class ManagerFactory {

    public static ItemManager getItemManager() {
        return ItemManager.getInstance();
    }

}
