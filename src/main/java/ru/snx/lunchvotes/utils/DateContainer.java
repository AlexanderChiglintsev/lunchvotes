package ru.snx.lunchvotes.utils;

import java.time.LocalDate;
import java.time.LocalTime;

public final class DateContainer {

    private static LocalDate globalDate = LocalDate.MIN;

    private static LocalTime globalTime = LocalTime.MIN;

    public static LocalDate getDate(){
        return globalDate.isEqual(LocalDate.MIN) ? LocalDate.now() : globalDate;
    }

    public static LocalTime getTime(){
        return globalTime.equals(LocalTime.MIN) ? LocalTime.now() : globalTime;
    }

    public static void setDate(LocalDate localDate) {
        globalDate = localDate;
    }

    public static void setTime(LocalTime localTime) {
        globalTime = localTime;
    }
}
