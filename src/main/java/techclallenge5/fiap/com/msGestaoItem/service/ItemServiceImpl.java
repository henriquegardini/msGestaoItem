package techclallenge5.fiap.com.msGestaoItem.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.exception.ItemNotFoundException;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Flux<Item> buscarItens() {
        return itemRepository.findAll();
    }

    public Mono<Item> buscarItemPeloID(String id) {
        return itemRepository.findById(id);
    }

    public Mono<Item> criarItem(Item item) {
        return itemRepository.save(item);
    }

    public Mono<Item> atualizarItem(Item item) {
        var itemBuscado = itemRepository.findById(item.getId());
        if (itemBuscado.blockOptional().isPresent()) {
            return itemRepository.save(item);
        } else {
            return Mono.error(new ItemNotFoundException());
        }
    }

    public Mono<Void> deleteItem(String idItem) {
        var item = itemRepository.findById(idItem);
        if (item.blockOptional().isPresent()) {
            return itemRepository.deleteById(idItem);
        } else {
            return Mono.error(new ItemNotFoundException());
        }
    }

}