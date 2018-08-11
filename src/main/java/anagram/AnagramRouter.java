package anagram;

import anagram.permutation.PermutationHandler;
import anagram.uppercase.UppercaseHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class AnagramRouter {
    @Bean
    public RouterFunction<ServerResponse> route(PermutationHandler permutationHandler, UppercaseHandler uppercaseHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/anagram/{string}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), permutationHandler::generatePermutation)
                .andRoute(RequestPredicates.GET("/anagram/uppercase/{string}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), uppercaseHandler::generateUppercase);

    }
}