package resources.languages;

public class LanguageFactory {

    public static Language getLanguage(String lang) {
        switch (lang) {
            case "Chinese": return Language.CHINESE;
            case "English": return Language.ENGLISH;
            case "French": return Language.FRENCH;
            case "German": return Language.GERMAN;
            case "Italian": return Language.ITALIAN;
            case "Portuguese": return Language.PORTUGUESE;
            case "Russian": return Language.RUSSIAN;
            case "Spanish": return Language.SPANISH;
            case "Syntax": return Language.SYNTAX;
            default: throw new IllegalArgumentException();
        }
    }
}
