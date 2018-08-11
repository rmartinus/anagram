package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    public static void sleep(long millis) {
        try {
            LOGGER.info("Sleeping for {} milliseconds", millis);
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}