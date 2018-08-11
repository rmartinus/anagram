package anagram.permutation;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PermutationTest {
    @Test
    public void shouldReturnTheSameGivenOneLetterString() {
        assertThat(Permutation.generatePermutation("a")).containsOnlyOnce("a");
    }

    @Test
    public void shouldReturnAllPossibleCombosGivenTwoLetters() {
        assertThat(Permutation.generatePermutation("ab")).containsOnlyOnce("ba", "ab");
    }

    @Test
    public void shouldReturnAllPossibleCombosGivenThreeLetters() {
        assertThat(Permutation.generatePermutation("abc")).containsOnlyOnce(
                "abc", "cab", "bca",
                "acb", "cba", "bac");
    }

    @Test
    public void shouldReturnAllPossibleCombosGivenFourLetters() {
        assertThat(Permutation.generatePermutation("abcd")).containsOnlyOnce(
                "acbd", "adbc", "bcad", "bdac", "cbad", "cdab", "abdc", "acdb", "bacd", "bdca", "cabd", "cdba",
                "dbac", "dcab", "abcd", "adcb", "badc", "bcda", "dcba", "dabc", "cbda", "cadb", "dbca", "dacb"
        );
    }

    @Test
    public void shouldSwapCharacters() {
        assertThat(Permutation.swap("abcd", 0, 2)).isEqualTo("cbad");
    }
}