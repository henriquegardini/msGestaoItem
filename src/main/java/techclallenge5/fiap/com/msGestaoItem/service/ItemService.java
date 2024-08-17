package techclallenge5.fiap.com.msGestaoItem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.Model.Item;
import techclallenge5.fiap.com.msGestaoItem.dto.ItemResquestDTO;
import techclallenge5.fiap.com.msGestaoItem.exception.ItemNotFoundException;
import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;
import techclallenge5.fiap.com.msGestaoItem.request.ItemRequest;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Flux<Item> buscarItens() {
        return itemRepository.findAll();
    }

    public Mono<Item> criarItem(ItemRequest itemRequest) {
        var item = new Item();
        item.setNome(itemRequest.nome);
        return itemRepository.save(item);
    }

    public Mono<Item> atualizarItem(ItemResquestDTO itemResquestDTO) {
        var item = itemRepository.findById(itemResquestDTO.getId());
        if (item.blockOptional().isPresent()) {
            var itemAtualizado = new Item();
            itemAtualizado.setId(itemResquestDTO.getId());
            itemAtualizado.setNome(itemResquestDTO.getNome());
            return itemRepository.save(itemAtualizado);
        } else {
            throw new ItemNotFoundException();
        }
    }

    public Mono<Void> deleteItem(String idItem) {
        var item = itemRepository.findById(idItem);
        if (item.blockOptional().isPresent()) {
            return itemRepository.deleteById(idItem);
        } else {
            throw new ItemNotFoundException();
        }
    }

}