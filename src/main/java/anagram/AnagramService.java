package anagram;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Set;

@Component
public class AnagramService {
    public Flux<AnagramData> generateAnagramPermutation(String string) {
        Set<String> strings = Anagram.generatePermutation(string);
        AnagramData anagramData = new AnagramData();
        anagramData.setAnagram(strings);
        return Flux.just(anagramData);
    }
}