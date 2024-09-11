package techclallenge5.fiap.com.msGestaoItem.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.model.Item;

public interface ItemRepository extends ReactiveCrudRepository<Item, Long> {

    Flux<Item> findAll();

    Mono<Item> findById(Long idItem);

    Mono<Void> deleteById(Long idItem);

}
