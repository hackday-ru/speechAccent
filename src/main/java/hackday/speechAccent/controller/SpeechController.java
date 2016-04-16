package hackday.speechAccent.controller;

import hackday.speechAccent.model.Languages;
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

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Languages getLanguages() {
        return new Languages();
    }
}
