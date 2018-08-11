package anagram.permutation;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class PermutationData {
    private String input;
    private Set<String> anagram;
}