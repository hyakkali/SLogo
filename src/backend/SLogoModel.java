package backend;

import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import resources.languages.Language;

public class SLogoModel {

    SLogoData myData;
    Executor myExecutor;

    public SLogoModel() {
        myExecutor = new Executor();
    }

    public void setLanguage(Language lang) {
        myExecutor.setMyLanguage(lang);
    }

    @SuppressWarnings("serial")
    public void parse(String input) {
        myExecutor.parseText(myData, new ArrayList<String>() {{
            for (String str : input.split("\\s+")) add(str.trim());
        }}).evaluate();
    }
}
