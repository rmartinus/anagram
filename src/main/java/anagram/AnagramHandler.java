package anagram;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class AnagramHandler {

    public Mono<ServerResponse> generateAnagramPermutation(ServerRequest serverRequest) {
        Set<String> stringSet = Anagram.generatePermutation(serverRequest.pathVariable("string"));
        AnagramData anagramData = new AnagramData();
        anagramData.setAnagram(stringSet);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(anagramData), AnagramData.class);
    }
}