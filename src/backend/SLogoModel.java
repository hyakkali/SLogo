package backend;

import java.util.ArrayList;

import resources.languages.Language;
import main.Controller;

public class SLogoModel {

    SLogoData myData;
    Executor myExecutor;

    public SLogoModel(Controller ctrl) {
        myExecutor = new Executor(ctrl);
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
