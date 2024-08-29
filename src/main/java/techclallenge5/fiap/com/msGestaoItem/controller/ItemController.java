package techclallenge5.fiap.com.msGestaoItem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Flux<Item>> buscarItens() {
        return ResponseEntity.ok(itemService.buscarItens());
    }

    @GetMapping("/{idItem}")
    public ResponseEntity<Mono<Item>> buscarItemPeloID(@PathVariable String idItem) {
        return ResponseEntity.ok(itemService.buscarItemPeloID(idItem));
    }

    @PostMapping
    public ResponseEntity<Mono<Item>> criarItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.criarItem(item));
    }

    @PatchMapping
    public ResponseEntity<Mono<Item>> atualizarItem(@RequestBody Item item) {
        return ResponseEntity.ok(itemService.atualizarItem(item));
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Mono<Void>> deleteItem(@PathVariable String idItem) {
        return ResponseEntity.ok(itemService.deleteItem(idItem));
    }

}