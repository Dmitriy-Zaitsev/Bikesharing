package by.epam.bikesharing.resource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageManager {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private MessageManager() { }

    public static String getProperty(String key) {
        String val = resourceBundle.getString(key);
        return new String(val.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }

    public static void setLocale(String language) {
        String country = language.toUpperCase();
        Locale locale = new Locale(language, country);
        resourceBundle = ResourceBundle.getBundle("messages", locale);
        System.out.println();
    }
}