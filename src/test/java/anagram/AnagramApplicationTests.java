package anagram;

import anagram.permutation.Permutation;
import anagram.permutation.PermutationData;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class AnagramApplicationTests {

    @Test
    public void shouldReturnOneAnagram() {
        AnagramWebClient client = new AnagramWebClient();
        Mono<PermutationData> dataMono = client.getAnagrams("reactive");
        PermutationData data = dataMono.block();

        assertThat(data.getInput()).isEqualTo("REACTIVE");
        assertThat(data.getAnagram()).isEqualTo(Permutation.generatePermutation("REACTIVE"));
    }

    @Test
    @Ignore
    public void shouldReturnAnagrams() {
        AnagramWebClient client0 = new AnagramWebClient();
        Mono<PermutationData> data0Mono = client0.saveAnagrams("Reactive");
        data0Mono.block();

        AnagramWebClient client1 = new AnagramWebClient();
        Mono<PermutationData> data1Mono = client1.getAnagrams("Reactive");

        AnagramWebClient client2 = new AnagramWebClient();
        Mono<PermutationData> data2Mono = client2.getAnagrams("Programming");

        AnagramWebClient client3 = new AnagramWebClient();
        Mono<PermutationData> data3Mono = client3.getAnagrams("Whoop!");

        data1Mono.block();
        data2Mono.block();
        data3Mono.block();
    }

    @Test
    public void shouldSaveAnagrams() {
        AnagramWebClient client = new AnagramWebClient();
        Mono<PermutationData> dataMono = client.saveAnagrams("Reactive");

        PermutationData data = dataMono.block();
        assertThat(data.getInput()).isEqualTo("REACTIVE");
        assertThat(data.getAnagram()).isEqualTo(Permutation.generatePermutation("REACTIVE"));
    }
}