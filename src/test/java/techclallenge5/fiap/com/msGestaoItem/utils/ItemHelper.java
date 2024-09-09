//package techclallenge5.fiap.com.msGestaoItem.utils;
//
//import reactor.core.publisher.Mono;
//import techclallenge5.fiap.com.msGestaoItem.model.Item;
//import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;
//
//import java.math.BigDecimal;
//
//public abstract class ItemHelper {
//
//    public static Item gerarItem() {
//        return Item.builder()
//                .id(1L)
//                .descricao("descricao")
//                .quantidade(BigDecimal.ONE)
//                .precoTotal(BigDecimal.valueOf(100.00))
//                .build();
//    }
//
//    public static Item gerarItemNaoExistente() {
//        return Item.builder()
//                .id(2L)
//                .descricao("descricao")
//                .quantidade(BigDecimal.ONE)
//                .precoTotal(BigDecimal.valueOf(100.00))
//                .build();
//    }
//
//    public static Item gerarItemAtualizacao() {
//        return Item.builder()
//                .id(1L)
//                .descricao("descricao atualizado")
//                .quantidade(BigDecimal.ONE)
//                .precoTotal(BigDecimal.valueOf(200.00))
//                .build();
//    }
//
//    public static Mono<Item> registrarMensagem(ItemRepository repository) {
//        var item = gerarItem();
//        return repository.save(item);
//    }
//}
