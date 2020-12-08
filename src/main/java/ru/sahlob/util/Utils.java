package ru.sahlob.util;

public class Utils {

    public static final String NUM_REGLEX = "\\d+";


    public static boolean checkTheStringContainsOnlyNumbers(String message) {
        return message.matches(NUM_REGLEX);
    }

    public static boolean checkTheStringContainsOnlyNumbersBetweenInRange(String message, int min, int max) {
        var flag = false;
        if (message.matches(NUM_REGLEX)) {
            int num = Integer.parseInt(message);
            if (num >= min && num <= max) {
                flag = true;
            }
        }
        return flag;
    }
}
