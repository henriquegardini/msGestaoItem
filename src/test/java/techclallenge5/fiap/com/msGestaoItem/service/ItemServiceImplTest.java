
package techclallenge5.fiap.com.msGestaoItem.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import techclallenge5.fiap.com.msGestaoItem.exception.ItemNotFoundException;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;
import techclallenge5.fiap.com.msGestaoItem.utils.ItemHelper;

class ItemServiceImplTest {

    @InjectMocks
    private ItemServiceImpl itemServiceImpl;
    @Mock
    private ItemRepository itemRepository;
    AutoCloseable openMocks;
    private Item itemMock = ItemHelper.gerarItem();
    private Item itemNaoExistenteMock = ItemHelper.gerarItemNaoExistente();
    private Item itemAtualizacaoMock = ItemHelper.gerarItemAtualizacao();

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        itemServiceImpl = new ItemServiceImpl(itemRepository);
        BDDMockito.when(itemRepository.findAll()).thenReturn(Flux.just(itemMock));
        BDDMockito.when(itemRepository.findById(itemMock.getId())).thenReturn(Mono.just(itemMock));
        BDDMockito.when(itemRepository.findById(itemNaoExistenteMock.getId())).thenReturn(Mono.empty());
        BDDMockito.when(itemRepository.save(ItemHelper.gerarItem())).thenReturn(Mono.just(itemMock));
        BDDMockito.when(itemRepository.save(ItemHelper.gerarItemAtualizacao())).thenReturn(Mono.just(itemAtualizacaoMock));
        BDDMockito.when(itemRepository.deleteById(itemMock.getId())).thenReturn(Mono.empty());
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
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
        var item = ItemHelper.gerarItem();
        BDDMockito.when(itemRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(item));

        StepVerifier.create(itemServiceImpl.buscarItemPeloID("1"))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    void devePermitirCriarItensComSucesso() {
        var item = ItemHelper.gerarItem();

        StepVerifier.create(itemServiceImpl.criarItem(item))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    void devePermitirAtualizarItemComSucesso() {
        var itemAtualizacao = ItemHelper.gerarItemAtualizacao();

        StepVerifier.create(itemServiceImpl.atualizarItem(itemAtualizacao))
                .expectNext(itemAtualizacaoMock)
                .verifyComplete();
    }

    @Test
    void devePermitirAtualizarItemSemSucesso() {
        var itemNaoExistente = ItemHelper.gerarItemNaoExistente();

        StepVerifier.create(itemServiceImpl.atualizarItem(itemNaoExistente))
                .expectError(ItemNotFoundException.class)
                .verify();
    }

    @Test
    void devePermitirDeleteItemComSucesso() {
        var item = ItemHelper.gerarItem();

        StepVerifier.create(itemServiceImpl.deleteItem(item.getId()))
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