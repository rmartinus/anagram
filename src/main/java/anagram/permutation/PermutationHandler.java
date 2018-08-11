package anagram.permutation;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class PermutationHandler {

    public Mono<ServerResponse> generatePermutation(ServerRequest serverRequest) {
        String string = serverRequest.pathVariable("string");
        Set<String> stringSet = Permutation.generatePermutation(string);
        PermutationData permutationData = new PermutationData();
        permutationData.setInput(string);
        permutationData.setAnagram(stringSet);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(permutationData), PermutationData.class);
    }
}