package hackday.speechAccent.controller;

import hackday.speechAccent.dao.LanguageDao;
import hackday.speechAccent.dto.Rate;
import hackday.speechAccent.model.Languages;
import hackday.speechAccent.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;

/**
 * Created by nicaraguanec on 16.04.2016.
 */
@Controller
@MultipartConfig(fileSizeThreshold = 20971520)
public class SpeechController {

    @Autowired
    private LanguageDao languageDao;

    @Autowired
    private Utils utils;

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> getHead() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/language", method = RequestMethod.GET)
    public
    @ResponseBody
    Languages getLanguages() {
        return languageDao.getLanguagesWithText();
    }


    @RequestMapping(value = "/record", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createRecord(@RequestParam MultipartFile multipartFile,
                                             @RequestParam String origin,
                                             @RequestParam String accent,
                                             @RequestParam String text) {
        String filename = utils.saveFile(multipartFile);
        if (filename != null && languageDao.createRecord(origin, accent, text, filename)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public ResponseEntity<Void> rateRecord(@RequestParam String link,
                                           @RequestParam int rate) {
        if (languageDao.rateRecord(link, rate)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/rate", method = RequestMethod.GET)
    public
    @ResponseBody
    Rate getRate(@RequestParam String recordName) {
        return languageDao.getRate(recordName);
    }

}
