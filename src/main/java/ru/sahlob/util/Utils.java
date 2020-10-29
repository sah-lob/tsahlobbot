package ru.sahlob.util;

public class Utils {

    public static final String NUM_REGLEX = "\\d+";


    public static boolean checkTheStringContainsOnlyNumbers(String message) {
        return message.matches(NUM_REGLEX);
    }
}
