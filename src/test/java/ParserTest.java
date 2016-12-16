import jschool2016.translator.service.Utils.Parser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ParserTest {
    String text = "парсим слова по пробелам и другим значениям";

    @Test
    public void parseTextToWordsTest(){
        List<String> res1 = Parser.parseTextToWords(text);
        List <String> res2 = new ArrayList<String>();
        res2.addAll(new ArrayList<String>(Arrays.asList("парсим", "слова", "по", "пробелам", "и", "другим", "значениям")));

        assertNotNull(res1);
        assertEquals(res1, res2);
    }
}
