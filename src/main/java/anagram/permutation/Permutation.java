package anagram.permutation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

public class Permutation {
    private static final Logger LOGGER = LoggerFactory.getLogger(Permutation.class);

    public static Set<String> generatePermutation(String string) {
        Set<String> result = new HashSet<>();
        traverse(string, 0, string.length() - 1, result);
        return result;
    }

    private static void traverse(String string, int startIndex, int endIndex, Set<String> permutation) {
        LOGGER.debug("Traversing string {} startIndex {} endIndex {}", string, startIndex, endIndex);

        if (startIndex == endIndex) {
            permutation.add(string);
        } else {
            for (int i = startIndex; i <= endIndex; i++) {
                string = swap(string, startIndex, i);
                traverse(string, startIndex + 1, endIndex, permutation);
            }
        }
    }

    static String swap(String string, int sourceIndex, int targetIndex) {
        char tempChar;
        char[] chars = string.toCharArray();
        tempChar = chars[sourceIndex];
        chars[sourceIndex] = chars[targetIndex];
        chars[targetIndex] = tempChar;

        return String.valueOf(chars);
    }
}