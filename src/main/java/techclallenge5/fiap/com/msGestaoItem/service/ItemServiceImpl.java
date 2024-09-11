package techclallenge5.fiap.com.msGestaoItem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.exception.ItemNotFoundException;
import techclallenge5.fiap.com.msGestaoItem.exception.PrecoItemNotFoundException;
import techclallenge5.fiap.com.msGestaoItem.feign.ProdutoClient;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.model.Produto;
import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ProdutoClient produtoClient;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ProdutoClient produtoClient) {
        this.itemRepository = itemRepository;
        this.produtoClient = produtoClient;
    }

    public Flux<Item> buscarItens() {
        return itemRepository.findAll();
    }

    public Mono<Item> buscarItemPeloID(Long id) {
        return itemRepository.findById(id);
    }

    public Mono<Item> criarItem(Item item) {
        var produto = buscaProduto(item.getIdProduto());
        calcularPrecoTotal(item, produto);
        return itemRepository.save(item);
    }

    public Mono<Item> atualizarItem(Item item) {
        var itemBuscado = itemRepository.findById(item.getId());
        if (itemBuscado.blockOptional().isPresent()) {
            var produto = buscaProduto(item.getIdProduto());
            calcularPrecoTotal(item, produto);
            return itemRepository.save(item);
        } else {
            return Mono.error(new ItemNotFoundException());
        }
    }

    public Mono<Void> deleteItem(Long idItem) {
        var item = itemRepository.findById(idItem);
        if (item.blockOptional().isPresent()) {
            return itemRepository.deleteById(idItem);
        } else {
            return Mono.error(new ItemNotFoundException());
        }
    }

    private Produto buscaProduto(Long id) {
        try {
            return produtoClient.getProdutoById(id);
        } catch (Exception e) {
            throw new RuntimeException("SERVIÇO DE GESTÃO DE PRECOS: Ocorreu um problema na busca do produto. Exceção: ", e);
        }
    }

    private static void calcularPrecoTotal(Item item, Produto produto) {
        if (produto.getPrecoAtualUnitario() == null) {
            throw new PrecoItemNotFoundException();
        } else {
            item.setPrecoTotal(produto.getPrecoAtualUnitario().multiply(item.getQuantidade()));
        }
    }

}