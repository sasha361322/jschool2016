package jschool2016.translator.controller;

import jschool2016.translator.service.Translator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class TranslateController {

    @RequestMapping(value = "/addText", method = POST)
    public String addText(@ModelAttribute("text") String  text, ModelMap modelMap) throws Exception {
        modelMap.addAttribute("pairs", Translator.gerTranslatedList(text));
        return "pair/approve";
    }
}
