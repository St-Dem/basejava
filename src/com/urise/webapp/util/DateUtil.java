package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static final LocalDate NOW = LocalDate.of(3000, 1, 1);

    public static LocalDate of(int year, Month month) {
        return LocalDate.of(year, month, 1);
    }

    public static String toHtml(LocalDate localDate) {
        int month = localDate.getMonthValue();
        return localDate.equals(NOW)
                ? " Сейчас "
                : (month > 9 ? String.valueOf(month) : ("0" + month)) + "/" + localDate.getYear();
    }
}
