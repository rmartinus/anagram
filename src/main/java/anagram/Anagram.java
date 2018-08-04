package anagram;

import java.util.HashSet;
import java.util.Set;

public class Anagram {
    public static Set<String> generate(String string) {
        Set<String> result = new HashSet<>();
        traverse(string, 0, string.length() - 1, result);
        return result;
    }

    private static void traverse(String string, int startIndex, int endIndex, Set<String> permutation) {
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