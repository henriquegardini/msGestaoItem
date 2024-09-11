package techclallenge5.fiap.com.msGestaoItem.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.model.Item;

public interface ItemRepository extends ReactiveMongoRepository<Item, Long> {

    Flux<Item> findAll();

    Mono<Item> findById(String idItem);

    Mono<Void> deleteById(String idItem);

}
