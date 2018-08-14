package anagram.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AnagramRepository extends ReactiveMongoRepository<AnagramEntity, String> {
    Flux<AnagramEntity> findByString(String string);
}