package anagram;

import java.util.Collections;
import java.util.List;

public class Anagram {
    /**
     * ab -> ab, ba
     */
    public static List<String> generate(String string) {
        return Collections.singletonList(string);
    }
}
