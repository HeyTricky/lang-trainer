import com.dsr.javaschool.langtrainer.utils.TextParser;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class TextParserTest {

    @Test
    public void parseTextWithPhrasalVerbs() {
        String text = "i get up early";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 5);
        assertTrue(words.contains("i"));
        assertTrue(words.contains("get"));
        assertTrue(words.contains("get up"));
        assertTrue(words.contains("up"));
        assertTrue(words.contains("early"));
    }

    @Test
    public void parseTextWithDuplicatePhrasalVerbs() {
        String text = "get up look up";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 5);
        assertTrue(words.contains("get"));
        assertTrue(words.contains("get up"));
        assertTrue(words.contains("up"));
        assertTrue(words.contains("look"));
        assertTrue(words.contains("look up"));
    }

    @Test
    public void parseTextWithSeveralPrepositions() {
        String text = "look after up";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 4);
        assertTrue(words.contains("look"));
        assertTrue(words.contains("after"));
        assertTrue(words.contains("look after"));
        assertTrue(words.contains("up"));
    }

    @Test
    public void parseTextWithFirstPreposition() {
        String text = "at look after";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 4);
        assertTrue(words.contains("look"));
        assertTrue(words.contains("after"));
        assertTrue(words.contains("look after"));
        assertTrue(words.contains("at"));
    }

    @Test
    public void parseTextWithArticles() {
        String text = "The morning, a key, an apple";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 3);
        assertTrue(words.contains("morning"));
        assertTrue(words.contains("key"));
        assertTrue(words.contains("apple"));
    }

    @Test
    public void parseTextWithDuplicates() {
        String text = "cat cat dog cat dog";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 2);
        assertTrue(words.contains("cat"));
        assertTrue(words.contains("dog"));
    }

    @Test
    public void parseTextBothLang() {
        String text = "кошка cat кошкаcat кошка-cat";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 3);
        assertTrue(words.contains("кошка"));
        assertTrue(words.contains("cat"));
        assertTrue(words.contains("кошкаcat"));
    }

    @Test
    public void parseTextWithUpperCase() {
        String text = "CaT DOG carrot";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 3);
        assertTrue(words.contains("cat"));
        assertTrue(words.contains("dog"));
        assertTrue(words.contains("carrot"));
    }

    @Test
    public void parseTextWithSeps() {
        String text = "cat! dog..  \n-carrot_parrot?";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 4);
        assertTrue(words.contains("cat"));
        assertTrue(words.contains("dog"));
        assertTrue(words.contains("carrot"));
        assertTrue(words.contains("parrot"));
    }

    @Test
    public void parseTextEmpty() {
        String text = "";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 0);
    }

    @Test
    public void parseTextWithOneWord() {
        String text = "cat";
        List<String> words = TextParser.parse(text);
        assertTrue(words.size() == 1);
        assertTrue(words.contains("cat"));
    }
}
