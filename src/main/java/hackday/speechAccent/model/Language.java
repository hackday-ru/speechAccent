package hackday.speechAccent.model;

import java.util.List;
import java.util.Map;

/**
 * Created by nicaraguanec on 16.04.2016.
 */
public class Language {
    private String isoName;
    private String text;
    private Map<String, String> accents;

    public Language() {}

    public Language(String name, String text) {
        this.isoName = name;
        this.text = text;
    }

    public String getIsoName() {
        return isoName;
    }

    public void setIsoName(String isoName) {
        this.isoName = isoName;
    }

    public Map<String, String> getAccents() {
        return accents;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAccents(Map<String, String> accents) {
        this.accents = accents;
    }


}
