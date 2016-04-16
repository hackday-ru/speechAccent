package hackday.speechAccent.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by nicaraguanec on 16.04.2016.
 */
public class Languages implements Serializable {

    private List<Language> languages;

    public Languages() {}

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    class Language {
        public Language() {}

        public Language(String name, List<String> accents) {
            this.name = name;
            this.accents = accents;
        }

        private String name;
        private List<String> accents;

        public String getName() {
            return name;
        }

        public List<String> getAccents() {
            return accents;
        }
    }
}
