package jschool2016.translator.controller;

import jschool2016.translator.dto.PairDTO;
import jschool2016.translator.entity.Pair;
import jschool2016.translator.service.PairService;
import jschool2016.translator.service.Utils.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/pair")
@SessionAttributes({"idsOfAddedWords", "pairsForTest"})
public class PairController {
//    public static final String uploadingdir = System.getProperty("user.dir") + "/uploadingdir/";

    @RequestMapping("/upload")
    public String uploading(Model model) {
//        File file = new File(uploadingdir);
//        model.addAttribute("files", file.listFiles());
//        return "uploading";
        return "redirect:/";

    }
    @RequestMapping(value = "/upload", method = POST)
    public String uploadingPost(@RequestParam("uploadingFiles") MultipartFile uploadedFile) throws IOException {
//        Scanner sc = new Scanner(uploadedFile.getInputStream());
//        while (sc.hasNext()){
//            System.out.println(sc);
//        }
//        System.out.println(uploadingdir);
//        System.out.println(uploadedFile.getOriginalFilename());
//        File file = new File(uploadingdir + uploadedFile.getOriginalFilename());
//        uploadedFile.transferTo(new File(uploadingdir));
        return "redirect:/";
    }


    @Autowired
    private PairService pairService;

    @RequestMapping(value = "", method = GET)
    public String getAll(ModelMap modelMap) throws Exception {
        if (!modelMap.containsKey("idsOfAddedWords"))
            modelMap.addAttribute("idsOfAddedWords", new ArrayList<Long>());
        modelMap.addAttribute("pairs", pairService.getAll());
        return "pair/list";
    }

    @RequestMapping(value = "/add", method = GET)
    public String add(ModelMap modelMap) throws Exception {
        return "pair/add";
    }

    @RequestMapping(value = "/add", method = POST)
    public String create(@ModelAttribute("pair") Pair pair, ModelMap modelMap) {
        pair.setPriority(5);
        if (!modelMap.containsKey("idsOfAddedWords"))
            modelMap.addAttribute("idsOfAddedWords", new ArrayList<Long>());
        ArrayList<Long> ids = (ArrayList<Long>)modelMap.get("idsOfAddedWords");
        ids.add(pairService.save(pair).getId());
        modelMap.addAttribute("idsOfAddedWords", ids);
        return "redirect:/pair";
    }

    @RequestMapping(value = "/addPairs", method = POST)
    public String addPairs(@ModelAttribute("pairs") String pairs, ModelMap modelMap) {
        if (!modelMap.containsKey("idsOfAddedWords"))
            modelMap.addAttribute("idsOfAddedWords", new ArrayList<Long>());
        ArrayList<Long> ids = (ArrayList<Long>)modelMap.get("idsOfAddedWords");
        ArrayList<PairDTO> pairDTOS = Parser.getPairDTOListFromJSON(pairs);
        for (Pair pair : pairService.save(pairDTOS)){
            ids.add(pair.getId());
        }
        modelMap.clear();
        modelMap.addAttribute("idsOfAddedWords", ids);
        return "redirect:/pair";
    }


    @RequestMapping(value = "/addFile", method = POST)
    public void addPairs(@RequestParam("file") MultipartFile file, ModelMap modelMap) {
        if (!modelMap.containsKey("idsOfAddedWords"))
            modelMap.addAttribute("idsOfAddedWords", new ArrayList<Long>());
        ArrayList<Long> ids = (ArrayList<Long>)modelMap.get("idsOfAddedWords");
        try {
            Scanner sc = new Scanner(String.valueOf(file.getBytes()));
            while (sc.hasNext())
                System.out.println(sc.next());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/test", method = GET)
    public String test(ModelMap modelMap) throws Exception {
        return "test/beforeTest";
    }

    @RequestMapping(value = "/choose", method = POST)
    public String choose(ModelMap modelMap,
                         SessionStatus status,
                         @ModelAttribute("count") int count,
                         @ModelAttribute("from") String from) throws Exception {
        List<Pair> pairs = new ArrayList<Pair>();
        if (!modelMap.containsKey("idsOfAddedWords"))
            modelMap.addAttribute("idsOfAddedWords", new ArrayList<Long>());
        List<Long> ids = (ArrayList<Long>)modelMap.get("idsOfAddedWords");
        if ((from.equals("fromClient")&&(!ids.isEmpty()))){
            pairs.addAll(pairService.getByIds(ids));
            pairs.addAll(pairService.getRandomPairsExceptOfIds(count - ids.size(), ids));
        }
        else {
            pairs.addAll(pairService.getRandomPairs(count));
            status.setComplete();
        }
        modelMap.addAttribute("pairsForTest", pairs);
        return "test/test";
    }

    @RequestMapping(value = "/startTest", method = GET)
    public String start(ModelMap modelMap) throws Exception {
        List<Pair> pairs = (ArrayList<Pair>)modelMap.get("pairsForTest");
        Collections.shuffle(pairs);
        modelMap.addAttribute("pairs", pairs);
        modelMap.addAttribute("pairsForTest", pairs);
        return "test/doTest";
    }

    @RequestMapping(value = "/checkTest", method = POST)
    public String check(@ModelAttribute("pairs") String pairs,
                        ModelMap modelMap,
                        SessionStatus status) {
        List<Pair> correctPairs = (ArrayList<Pair>)modelMap.get("pairsForTest");
        status.setComplete();
        System.out.println(pairs);
        modelMap.remove("pairsForTest");
        modelMap.addAttribute("pairs", pairs);
        return "test/doTest";
    }
}
