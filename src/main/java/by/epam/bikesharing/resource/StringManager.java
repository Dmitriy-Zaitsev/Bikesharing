package by.epam.bikesharing.resource;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

public class StringManager {
    //INSTANCE;
    private static final String RU = "ru";
    private static final String US = "us";
    private static final ResourceBundle resourceBundleDefault = ResourceBundle.getBundle("strings");
    private static final ResourceBundle resourceBundleRu = ResourceBundle.getBundle("strings", new Locale("ru", "RU"));
    private static final ResourceBundle resourceBundleUs = ResourceBundle.getBundle("strings", new Locale("us", "US"));
    private static StringManager INSTANCE = new StringManager();

    private StringManager() {}

    public static StringManager getInstance() {
        return INSTANCE;
    }

    public String get(String key, String locale) {
        switch (locale) {
            case RU: return resourceBundleRu.getString(key);
            case US: return resourceBundleUs.getString(key);
            default: return resourceBundleDefault.getString(key);
        }
    }
}