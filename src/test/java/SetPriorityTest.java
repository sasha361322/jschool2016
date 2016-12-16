import jschool2016.translator.Conf;
import jschool2016.translator.entity.Pair;
import jschool2016.translator.service.PairService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Conf.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SetPriorityTest {

    @Autowired
    private PairService pairService;

    @Test
    public void bookSavingTest() {
        Pair newPair = new Pair();
        newPair.setEnWord("test");
        newPair.setRuWord("тест");
        Pair pair = pairService.save(newPair);
        assertNotNull(pair);
        assertEquals(5, pair.getPriority());
    }
}