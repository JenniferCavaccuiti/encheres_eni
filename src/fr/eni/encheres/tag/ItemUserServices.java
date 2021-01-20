package fr.eni.encheres.tag;

import fr.eni.encheres.models.bo.User;

import java.util.List;

public final class ItemUserServices {

    private ItemUserServices() {

    }

    public static String getUserLogin(Integer idSeller, List<User> usersList) {
        String login = null;
        for (User user: usersList) {
            if (user.getIdUser() == idSeller) {
                login = user.getLogin();
                break;
            }
        }
        return login;
    }
}
