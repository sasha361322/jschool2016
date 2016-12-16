package jschool2016.translator.controller;

import jschool2016.translator.service.Translator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class TranslateRestController {

    @RequestMapping(value = "/translate/{word}/{lang}", method = GET, produces = "text/html; charset=utf-8")
    public String translate(@PathVariable String word, @PathVariable String lang) throws Exception {
        return Translator.translate(word.toLowerCase(), lang);
    }

}
