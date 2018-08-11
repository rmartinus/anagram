package anagram;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.Disposable;
import util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.main.web-application-type=reactive", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AnagramApplicationTests {

    @Test
    public void shouldReturnAnagrams() {
        AnagramWebClient client = new AnagramWebClient();
        Disposable disposable = client.getAnagrams("abc");
        try {
            Util.sleep(20000);
        } finally {
            disposable.dispose();
        }
    }
}