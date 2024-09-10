
package techclallenge5.fiap.com.msGestaoItem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import techclallenge5.fiap.com.msGestaoItem.exception.ItemNotFoundException;
import techclallenge5.fiap.com.msGestaoItem.feign.ProdutoClient;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.model.Produto;
import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;
import techclallenge5.fiap.com.msGestaoItem.utils.ItemHelper;

class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemServiceImpl;
    @Mock
    private ProdutoClient produtoClient;
    @Mock
    private ItemRepository itemRepository;

    private final Item itemMock = ItemHelper.gerarItem();
    private final Item itemNaoExistenteMock = ItemHelper.gerarItemNaoExistente();
    private final Item itemAtualizacaoMock = ItemHelper.gerarItemAtualizacao();
    private final Produto produto = ItemHelper.gerarProduto();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        itemServiceImpl = new ItemServiceImpl(itemRepository, produtoClient);
        BDDMockito.when(produtoClient.getProdutoById(itemMock.getIdProduto()))
                .thenReturn(produto);
        BDDMockito.when(produtoClient.getProdutoById(itemAtualizacaoMock.getIdProduto()))
                .thenReturn(produto);
        BDDMockito.when(itemRepository.findAll())
                .thenReturn(Flux.just(itemMock));
        BDDMockito.when(itemRepository.findById(itemMock.getId().toString()))
                .thenReturn(Mono.just(itemMock));
        BDDMockito.when(itemRepository.findById(itemNaoExistenteMock.getId().toString()))
                .thenReturn(Mono.empty());
        BDDMockito.when(itemRepository.save(itemMock))
                .thenReturn(Mono.just(itemMock));
        BDDMockito.when(itemRepository.save(itemAtualizacaoMock))
                .thenReturn(Mono.just(itemAtualizacaoMock));
        BDDMockito.when(itemRepository.deleteById(itemMock.getId().toString()))
                .thenReturn(Mono.empty());
    }

    @Test
    void devePermitirBuscarItensComSucesso() {
        StepVerifier.create(itemServiceImpl.buscarItens())
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    void devePermitirBuscarItemPeloIDComSucesso() {
        StepVerifier.create(itemServiceImpl.buscarItemPeloID(itemMock.getId()))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    void devePermitirCriarItensComSucesso() {
        StepVerifier.create(itemServiceImpl.criarItem(itemMock))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    void devePermitirAtualizarItemComSucesso() {
        StepVerifier.create(itemServiceImpl.atualizarItem(itemAtualizacaoMock))
                .expectNext(itemAtualizacaoMock)
                .verifyComplete();
    }

    @Test
    void devePermitirAtualizarItemSemSucesso() {
        StepVerifier.create(itemServiceImpl.atualizarItem(itemNaoExistenteMock))
                .expectError(ItemNotFoundException.class)
                .verify();
    }

    @Test
    void devePermitirDeleteItemComSucesso() {
        StepVerifier.create(itemServiceImpl.deleteItem(itemMock.getId()))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void devePermitirDeleteItemSemSucesso() {
        StepVerifier.create(itemServiceImpl.deleteItem(itemNaoExistenteMock.getId()))
                .expectError(ItemNotFoundException.class)
                .verify();
    }

}