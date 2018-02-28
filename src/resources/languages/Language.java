package resources.languages;

import java.util.Set;
import java.util.ResourceBundle;

public enum Language {

    CHINESE("Chinese"),
    ENGLISH("English"),
    FRENCH("French"),
    GERMAN("German"),
    ITALIAN("ITALIAN"),
    PORTUGUESE("Portuguese"),
    RUSSIAN("Russian"),
    SPANISH("Spanish"),
    SYNTAX("Syntax");

    private String myLanguage;
    private ResourceBundle myResources;

    Language(String lang) {
        myLanguage = lang;
        myResources = ResourceBundle.getBundle("resources.languages/"+lang);
    }

    public String getLanguage(){
        return myLanguage;
    }

    public String getString(String key) {
        return myResources.getString(key);
    }

    public Set<String> getKeys() {
        return myResources.keySet();
    }
}
