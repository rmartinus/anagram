package anagram.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface AnagramRepository extends ReactiveMongoRepository<AnagramEntity, String> {
    Mono<AnagramEntity> findByString(String string);
}