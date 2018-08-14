package anagram.permutation;

import anagram.repository.AnagramEntity;
import anagram.repository.AnagramRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
public class PermutationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(PermutationHandler.class);

    @Autowired
    private AnagramRepository anagramRepository;

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

    public Mono<ServerResponse> getPermutation(ServerRequest serverRequest) {
        String string = serverRequest.pathVariable("string");
        Flux<PermutationData> permutationDataFlux = anagramRepository.findByString(string)
                .map(entity -> {
                    LOGGER.info("Found entity from db: {}", entity);
                    PermutationData permutationData = new PermutationData();
                    permutationData.setInput(entity.getString());
                    permutationData.setAnagram(entity.getPermutations());
                    return permutationData;
                });

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(permutationDataFlux.next(), PermutationData.class);
    }

    public Mono<ServerResponse> savePermutation(ServerRequest serverRequest) {
        String string = serverRequest.pathVariable("string");
        Set<String> stringSet = Permutation.generatePermutation(string);

        AnagramEntity entity = new AnagramEntity();
        entity.setString(string);
        entity.setPermutations(stringSet);

        Flux<PermutationData> permutationDataFlux = anagramRepository.findByString(string)
                .switchIfEmpty(anagramRepository.save(entity))
                .map(newEntity -> {
                    LOGGER.info("Entity either found or saved in mongodb: {}", entity);
                    PermutationData permutationData = new PermutationData();
                    permutationData.setInput(newEntity.getString());
                    permutationData.setAnagram(newEntity.getPermutations());
                    return permutationData;
                });

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(permutationDataFlux.next(), PermutationData.class);
    }
}