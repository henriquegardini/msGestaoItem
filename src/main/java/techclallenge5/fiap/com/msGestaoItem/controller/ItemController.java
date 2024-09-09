package techclallenge5.fiap.com.msGestaoItem.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.service.ItemService;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;


    @GetMapping
    public Flux<Item> buscarItens() {
        return itemService.buscarItens();
    }

    @GetMapping("/{idItem}")
    public Mono<Item> buscarItemPeloID(@PathVariable Long idItem) {
        return itemService.buscarItemPeloID(idItem);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Item> criarItem(@Valid @RequestBody Item item) {
        return itemService.criarItem(item);
    }

    @PatchMapping
    public Mono<Item> atualizarItem(@Valid @RequestBody Item item) {
        return itemService.atualizarItem(item);
    }

    @DeleteMapping("/{idItem}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletarItem(@PathVariable Long idItem) {
        return itemService.deleteItem(idItem);
    }

}