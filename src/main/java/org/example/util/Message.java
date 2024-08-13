package org.example.util;

public class Message {

    private Message() {
        throw new IllegalStateException("Utility class");
    }

    public static void printMessageToUser(String message, boolean newLine) {
        if (newLine) {
            System.out.println(message); //NOSONAR
        } else {
            System.out.print(message); //NOSONAR
        }
    }

    public static void printFormattedMessageToUser(String format, Object... args) {
        System.out.printf(format, args); //NOSONAR
    }
}
