package fr.eni.encheres.models.bll;

import java.util.Locale;

public class BaseManager {

    public static String prepareWord(String word) {
        word = word.toLowerCase(Locale.ROOT);
        word = word.trim();
        return word;
    }
}
