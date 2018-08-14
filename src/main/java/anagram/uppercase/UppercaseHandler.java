package anagram.uppercase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UppercaseHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(UppercaseHandler.class);

    public Mono<ServerResponse> generateUppercase(ServerRequest serverRequest) {
        String string = serverRequest.pathVariable("string");

        LOGGER.info("Converting string {} to uppercase", string);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_STREAM_JSON)
                .body(Mono.just(Uppercase.uppercase(string)), String.class);
    }
}