package hackday.speechAccent.controller;

import hackday.speechAccent.dao.LanguageDao;
import hackday.speechAccent.model.Languages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Created by nicaraguanec on 16.04.2016.
 */
@Controller
@RequestMapping("/")
public class SpeechController {

    @Autowired
    private LanguageDao languageDao;

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Languages getLanguages() {
        return languageDao.getLanguagesWithText();
    }
}
