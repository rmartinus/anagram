package anagram;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class AnagramService {
    public Mono<AnagramData> generateAnagramPermutation(String string) {
        Set<String> stringSet = Anagram.generatePermutation(string);
        AnagramData anagramData = new AnagramData();
        anagramData.setAnagram(stringSet);

        return Mono.just(anagramData);
    }
}