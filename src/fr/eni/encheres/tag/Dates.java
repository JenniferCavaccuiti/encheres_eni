package fr.eni.encheres.tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Dates {
    private Dates() {
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime getDate() {
        return LocalDateTime.now();
    }

    public static LocalDateTime addDaysToDate(int days, LocalDateTime date) {
        return date.plusDays(days);
    }
}

