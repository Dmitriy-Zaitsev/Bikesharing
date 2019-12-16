package by.epam.bikesharing.resource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public enum StringManager {
    INSTANCE;
    //RU, US;

    // private static ResourceBundle resourceBundleRu = ResourceBundle.getBundle("strings", new Locale("ru", "RU"));
    //private static ResourceBundle resourceBundleUs = ResourceBundle.getBundle("strings", new Locale("us", "US"));
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("strings");

    public String get(String key) {
        String val = resourceBundle.getString(key);
        return new String(val.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
    }

    public void setLocale(String language) {
        String country = language.toUpperCase();
        Locale locale = new Locale(language, country);
        resourceBundle = ResourceBundle.getBundle("strings", locale);
        System.out.println();
    }
}