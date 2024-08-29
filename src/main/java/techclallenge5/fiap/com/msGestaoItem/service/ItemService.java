
package techclallenge5.fiap.com.msGestaoItem.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.model.Item;

public interface ItemService {

    Flux<Item> buscarItens();

    Mono<Item> buscarItemPeloID(String idItem);

    Mono<Item> criarItem(Item item);

    Mono<Item> atualizarItem(Item item);

    Mono<Void> deleteItem(String idItem);

}