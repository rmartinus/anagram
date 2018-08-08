package anagram;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

public class AnagramWebClient {
    private WebClient webClient = WebClient.builder().baseUrl("http://localhost:8080").build();

    public Disposable getAnagrams(String string) {
        AtomicInteger counter = new AtomicInteger(0);
        return webClient.get()
                .uri("/anagram/{string}", string)
                .accept(MediaType.APPLICATION_STREAM_JSON)
                .exchange()
                .publishOn(Schedulers.single())
                .flatMapMany(response -> response.bodyToMono(AnagramData.class))
                .delayElements(Duration.ofMillis(1000))
                .subscribe(s -> System.out.println(counter.incrementAndGet() + " >>>>>>> " + s),
                        err -> System.out.println("Error: " + err),
                        () -> System.out.println("Anagram stream stopped"));
    }

    public static void main(String[] args) {
        AnagramWebClient anagramWebClient = new AnagramWebClient();
        Disposable disposable = anagramWebClient.getAnagrams("Hello");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            disposable.dispose();
        }
    }
}