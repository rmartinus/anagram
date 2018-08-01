package anagram;

import org.junit.Test;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;

public class AnagramTest {
    @Test
    public void shouldReturnTheSameGivenOneLetterString() {
        assertThat(Anagram.generate("a")).isEqualTo(singletonList("a"));
    }
}