package backend;

import java.util.regex.Pattern;
import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.AbstractMap.SimpleEntry;

import resources.constants.Constants;
import resources.languages.Language;



public class Parser{

    private List<Entry<String, Pattern>> mySymbols;
    private Language myLanguage;


    protected Parser(Language lang) {
        mySymbols = new ArrayList<>();
        myLanguage = lang;
        addPattern(lang);

    }

    protected String getSymbol(String text) {
        for (Entry<String, Pattern> entry : mySymbols) {
            if (match(text, entry.getValue())) {
                return entry.getKey();
            }
        }
        return Constants.INVALID_SYMBOL;
    }
    /**
     * add the valid keys for specified language into the list
     * of valid symbols "mySymbols"
     */

    private void addPattern(Language lang) {
        for (String key: lang.getKeys()) {
            String regex = lang.getString(key);
            mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
        }
    }

    private boolean match(String text, Pattern regex) {
        return regex.matcher(text).matches();
    }

    public List<Entry<String, Pattern>> getSymbols()
    {
        return mySymbols;
    }


}