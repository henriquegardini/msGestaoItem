package techclallenge5.fiap.com.msGestaoItem.utils;

import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;

public abstract class ItemHelper {

    public static Item gerarItem() {
        return Item.builder()
                .id("1")
                .descricao("descricao")
                .quantidade(1)
                .precoUnitario(100.0)
                .precoTotal(100.0)
                .build();
    }

    public static Item gerarItemNaoExistente() {
        return Item.builder()
                .id("2")
                .descricao("descricao")
                .quantidade(1)
                .precoUnitario(100.0)
                .precoTotal(100.0)
                .build();
    }

    public static Item gerarItemAtualizacao() {
        return Item.builder()
                .id("1")
                .descricao("descricao atualizado")
                .quantidade(2)
                .precoUnitario(100.0)
                .precoTotal(200.0)
                .build();
    }

    public static Mono<Item> registrarMensagem(ItemRepository repository) {
        var item = gerarItem();
        return repository.save(item);
    }
}
