package anagram;

import anagram.permutation.PermutationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import util.Util;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class AnagramWebClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AnagramWebClient.class);
    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    public Disposable getAnagrams(String string) {
        AtomicInteger counter = new AtomicInteger(0);
        return webClient.get()
                .uri("/anagram/uppercase/{string}", string)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(String.class))
                .flatMap(uppercaseString ->
                        webClient.get()
                                .uri("/anagram/{uppercaseString}", uppercaseString)
                                .accept(MediaType.APPLICATION_STREAM_JSON)
                                .exchange()
                                .flatMap(response -> response.bodyToMono(PermutationData.class))
                                .switchIfEmpty(
                                        webClient.get().uri("/anagram/generate/{uppercaseString}", uppercaseString)
                                                .accept(MediaType.APPLICATION_STREAM_JSON)
                                                .exchange()
                                                .flatMap(response -> response.bodyToMono(PermutationData.class))
                                                .delayElement(Duration.ofMillis(1000))
                                )
                )
                .subscribe(s -> LOGGER.info("{} >>>>>>> {}", counter.incrementAndGet(), s),
                        err -> LOGGER.info("Error: {}", err),
                        () -> LOGGER.info("Permutation stream stopped"));
    }

    public Disposable saveAnagrams(String string) {
        AtomicInteger counter = new AtomicInteger(0);
        return webClient.get()
                .uri("/anagram/uppercase/{string}", string)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(String.class))
                .flatMap(uppercaseString ->
                        webClient.post().uri("/anagram/{uppercaseString}", uppercaseString)
                                .accept(MediaType.APPLICATION_STREAM_JSON)
                                .exchange()
                                .flatMap(response -> response.bodyToMono(PermutationData.class))
                                .delayElement(Duration.ofMillis(1000))
                )
                .delayElement(Duration.ofMillis(1000))
                .subscribe(s -> LOGGER.info("{} >>>>>>> {}", counter.incrementAndGet(), s),
                        err -> LOGGER.info("Error: {}", err),
                        () -> LOGGER.info("Permutation stream stopped"));

    }

    public static void main(String[] args) {
        AnagramWebClient anagramWebClient = new AnagramWebClient();
        Disposable disposable = anagramWebClient.getAnagrams("Hello");
        try {
            Util.sleep(20000);
        } finally {
            disposable.dispose();
        }
    }
}