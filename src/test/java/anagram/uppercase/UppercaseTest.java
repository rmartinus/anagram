package anagram.uppercase;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UppercaseTest {
    @Test
    public void shouldReturnNullGivenNull() {
        assertThat(Uppercase.uppercase(null)).isNull();
    }

    @Test
    public void shouldUppercaseGivenString() {
        assertThat(Uppercase.uppercase("abcd")).isEqualTo("ABCD");
    }
}