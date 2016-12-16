package jschool2016.translator.controller;


import jschool2016.translator.service.PairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/rest/pair")
public class PairRestContoller {

    @Autowired
    private PairService pairService;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Long id) {
        pairService.delete(id);
    }
}
