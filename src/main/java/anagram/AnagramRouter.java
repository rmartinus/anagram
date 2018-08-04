package anagram;

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
    public RouterFunction<ServerResponse> route(AnagramHandler anagramHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/anagram/{string}")
                        .and(RequestPredicates.accept(MediaType.APPLICATION_STREAM_JSON)), anagramHandler::generateAnagramPermutation);

    }
}