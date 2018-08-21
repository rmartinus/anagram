package anagram;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;


@Configuration
@Profile("test")
public class TestConfig extends AbstractReactiveMongoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);

    private final Environment environment;

    public TestConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient reactiveMongoClient() {
        int port = environment.getProperty("local.mongo.port", Integer.class);
        LOGGER.info("Port {}", port);
        return MongoClients.create(String.format("mongodb://localhost:%d", port));
    }

    @Override
    protected String getDatabaseName() {
        return "anagram-test";
    }
}