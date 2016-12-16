package jschool2016.translator.service.Utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jschool2016.translator.dto.PairDTO;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static ArrayList<PairDTO> getPairDTOListFromJSON(String json){
        ArrayList <PairDTO> result = new ArrayList<PairDTO>();
        JsonElement jsonElement = new JsonParser().parse(json);
        JsonArray jsonElements = jsonElement.getAsJsonArray();
        for (JsonElement e: jsonElements) {
            PairDTO pairDTO= new PairDTO();
            pairDTO.setEnWord(e.getAsJsonObject().get("enWord").getAsString());
            pairDTO.setRuWord(e.getAsJsonObject().get("ruWord").getAsString());
            result.add(pairDTO);
        }
        return result;
    }

    public static List <String> parseTextToWords(String text){
        String[] words = text.split(regex);//парсим слова по пробелам и другим возможным разделителям
        List<String> result = new ArrayList<String>();
        for (String word : text.split(regex)){
            if (word.length() > 0)
                result.add(word.toLowerCase());
        }
        return result;
    }

    public static List<Long> parseprioritiesStringListToIntList(List<String> strList){
        List<Long> res = new ArrayList<Long>();
        for (int i = 0; i < 10; i ++){
            res.add(new Long(0));
        }
        for (String str : strList){
            String [] parse = str.split("[ ]+");
            int pos = Integer.parseInt(parse[0]);
            Long value = Long.parseLong(parse[1]);
            res.set(pos - 1, value);
        }
        return res;
    }

    private static String regex = "[ ,.?!<>'\"-+$%^&*()]+";
}
