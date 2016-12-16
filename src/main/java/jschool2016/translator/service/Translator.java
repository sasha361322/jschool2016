package jschool2016.translator.service;


import jschool2016.translator.dto.PairDTO;
import jschool2016.translator.service.Utils.Parser;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Translator {

    public static String translate (String translateString, String langFromTo){
        String urlStr = uri + "key=" + key + "&text=";
        try {
            URL urlObj = new URL(urlStr);
            HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes("text=" + URLEncoder.encode(translateString, "UTF-8") + "&lang=" + langFromTo);

            InputStream response = connection.getInputStream();
            String json = new java.util.Scanner(response).nextLine();
            int start = json.indexOf("[");
            int end = json.indexOf("]");
            String translated = json.substring(start + 2, end - 1);
            return translated;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<PairDTO> gerTranslatedList(String text){
        List<String> words = Parser.parseTextToWords(text);
        List <PairDTO> result = new ArrayList<PairDTO>();
        for (String s : words){
            PairDTO pairDTO = new PairDTO();
            pairDTO.setRuWord(s);
            pairDTO.setEnWord(translate(s, "ru-en"));
            result.add(pairDTO);
        }
        return result;
    }

    private static final String key = "trnsl.1.1.20161213T133208Z.1e712d6c62cdf7d5.178705844086f237b6de84e475934727153dd7df";
    private static final String uri = "https://translate.yandex.net/api/v1.5/tr.json/translate?";
}
