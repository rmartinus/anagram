package anagram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.Disposable;
import util.Util;

/**
 * This test currently needs mongodb to be started up locally
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnagramApplicationTests {

    @Test
    public void shouldReturnOneAnagram() {
        AnagramWebClient client = new AnagramWebClient();
        Disposable disposable = client.getAnagrams("reactive");

        try {
            Util.sleep(5000);
        } finally {
            disposable.dispose();
        }
    }

    @Test
    public void shouldReturnAnagrams() {
        AnagramWebClient client = new AnagramWebClient();
        Disposable disposable = client.getAnagrams("Reactive");

        AnagramWebClient client2 = new AnagramWebClient();
        Disposable disposable2 = client2.getAnagrams("Programming");

        AnagramWebClient client3 = new AnagramWebClient();
        Disposable disposable3 = client3.getAnagrams("Whoop!");

        try {
            while (!disposable.isDisposed() || !disposable2.isDisposed() || !disposable3.isDisposed()) {
                Util.sleep(5000);
            }
            Util.sleep(3000);
        } finally {
            disposable.dispose();
            disposable2.dispose();
            disposable3.dispose();
        }
    }

    @Test
    public void shouldSaveAnagrams() {
        AnagramWebClient client = new AnagramWebClient();
        Disposable disposable = client.saveAnagrams("Reactive");

        try {
            while (!disposable.isDisposed()) {
                Util.sleep(5000);
            }
            Util.sleep(3000);
        } finally {
            disposable.dispose();
        }
    }
}