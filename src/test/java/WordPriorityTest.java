import com.dsr.javaschool.langtrainer.TestConfiguration;
import com.dsr.javaschool.langtrainer.entity.Word;
import com.dsr.javaschool.langtrainer.service.WordService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WordPriorityTest {

    @Autowired
    private WordService wordService;

    @Before
    public void initDb() {
        wordService.save(new Word("car", "машина", 1, 1));
        wordService.save(new Word("apple", "яблоко", 2, 1));
        wordService.save(new Word("carrot", "морковь", 1, 0));
    }

    @Test
    public void getAllByPriority() {
        List<Word> words = wordService.getAllByPriority();
        assertTrue(words.size() == 3);
        assertTrue(words.get(0).getEnglish().equals("carrot"));
        assertTrue(words.get(1).getEnglish().equals("apple"));
        assertTrue(words.get(2).getEnglish().equals("car"));
    }

    @Test
    public void get2ByPriority() {
        List<Word> words = wordService.getNByPriority(2);
        assertTrue(words.size() == 2);
        assertTrue(words.get(0).getEnglish().equals("carrot"));
        assertTrue(words.get(1).getEnglish().equals("apple"));
    }

    @Test
    public void getGreaterByPriority() {
        List<Word> words = wordService.getNByPriority(10);
        assertTrue(words.size() == 3);
        assertTrue(words.get(0).getEnglish().equals("carrot"));
        assertTrue(words.get(1).getEnglish().equals("apple"));
        assertTrue(words.get(2).getEnglish().equals("car"));
    }

    @Test
    public void getAllWithZeroCountAll() {
        wordService.save(new Word("parrot", "попугай"));
        List<Word> words = wordService.getNByPriority(2);
        assertTrue(words.size() == 2);
        assertTrue(!words.get(0).getEnglish().equals(words.get(1).getEnglish()));
        assertTrue(words.get(0).getEnglish().equals("carrot") || words.get(0).getEnglish().equals("parrot"));
        assertTrue(words.get(1).getEnglish().equals("carrot") || words.get(1).getEnglish().equals("parrot"));
    }

}
