package anagram;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class AnagramData {
    private Set<String> anagram;
}