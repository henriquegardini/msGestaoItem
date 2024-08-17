package techclallenge5.fiap.com.msGestaoItem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.Model.Item;
import techclallenge5.fiap.com.msGestaoItem.dto.ItemResquestDTO;
import techclallenge5.fiap.com.msGestaoItem.request.ItemRequest;
import techclallenge5.fiap.com.msGestaoItem.service.ItemService;


@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ResponseEntity<Flux<Item>> buscarItens() {
        return ResponseEntity.ok(itemService.buscarItens());
    }

    @PostMapping
    public ResponseEntity<Mono<Item>> criarItem(@RequestBody ItemRequest itemRequest) {
        return ResponseEntity.ok(itemService.criarItem(itemRequest));
    }

    @PatchMapping
    public ResponseEntity<Mono<Item>> atualizarItem(@RequestBody ItemResquestDTO itemResquestDTO) {
        return ResponseEntity.ok(itemService.atualizarItem(itemResquestDTO));
    }

    @DeleteMapping("/{idItem}")
    public ResponseEntity<Mono<Void>> deleteItem(@PathVariable String idItem) {
        return ResponseEntity.ok(itemService.deleteItem(idItem));
    }

}