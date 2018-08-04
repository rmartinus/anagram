package anagram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/anagram")
public class AnagramController {

    @Autowired
    private AnagramService anagramService;

    @GetMapping("/{string}")
    public Flux<AnagramData> generateAnagramPermutation(@PathVariable String string) {
        return anagramService.generateAnagramPermutation(string);
    }
}