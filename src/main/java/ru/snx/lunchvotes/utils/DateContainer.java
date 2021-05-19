package ru.snx.lunchvotes.utils;

import java.time.LocalDate;

public final class DateContainer {

    private static LocalDate testDate = LocalDate.MIN;

    public static LocalDate getDate(){
        return testDate.isEqual(LocalDate.MIN) ? LocalDate.now() : testDate;
    }

    public static void setDate(LocalDate localDate) {
        testDate = localDate;
    }
}
