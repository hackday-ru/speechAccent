package hackday.speechAccent.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nicaraguanec on 16.04.2016.
 */
public class Languages implements Serializable {

    private List<Language> languages;

    public Languages() {}

    public Languages(List<Language> langList) {
        this.languages = langList;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }
}
