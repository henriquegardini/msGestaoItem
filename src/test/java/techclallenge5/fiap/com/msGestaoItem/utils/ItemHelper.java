package techclallenge5.fiap.com.msGestaoItem.utils;

import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.model.Produto;
import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class ItemHelper {

    public static Item gerarItem() {
        return Item.builder()
                .id("1")
                .idProduto(11L)
                .descricao("descricao")
                .quantidade(BigDecimal.ONE)
                .precoTotal(BigDecimal.TEN)
                .build();
    }

    public static Item gerarItemNaoExistente() {
        return Item.builder()
                .id("2")
                .idProduto(12L)
                .descricao("descricao")
                .quantidade(BigDecimal.ONE)
                .precoTotal(BigDecimal.TEN)
                .build();
    }

    public static Item gerarItemAtualizacao() {
        return Item.builder()
                .id("1")
                .idProduto(11L)
                .descricao("descricao atualizado")
                .quantidade(BigDecimal.ONE)
                .precoTotal(BigDecimal.TEN)
                .build();
    }

    public static Produto gerarProduto() {
        return Produto.builder()
                .produtoId(11L)
                .precoNormal(BigDecimal.valueOf(100.00))
                .dataInicioPromocao(LocalDate.now())
                .dataFimPromocao(LocalDate.now())
                .precoPromocional(BigDecimal.valueOf(99.99))
                .precoAtualUnitario(BigDecimal.valueOf(99.99))
                .build();
    }

    public static Mono<Item> registrarMensagem(ItemRepository repository) {
        var item = gerarItem();
        return repository.save(item);
    }
}
