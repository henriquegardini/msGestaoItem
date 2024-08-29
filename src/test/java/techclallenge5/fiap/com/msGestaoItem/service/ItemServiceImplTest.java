
package techclallenge5.fiap.com.msGestaoItem.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
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
    private Item itemAtualizadoMock = ItemHelper.gerarItemAtualizacao();

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        itemServiceImpl = new ItemServiceImpl(itemRepository);
        BDDMockito.when(itemRepository.save(ItemHelper.gerarItem()))
                .thenReturn(Mono.just(itemMock));
        BDDMockito.when(itemRepository.findAll())
                .thenReturn(Flux.just(itemMock));
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirBuscarItens() {
        var itemGerado = ItemHelper.gerarItem();
        BDDMockito.when(itemRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(itemGerado));

        StepVerifier.create(itemServiceImpl.buscarItens())
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    void devePermitirBuscarItemPeloID() {
        var itemGerado = ItemHelper.gerarItem();
        BDDMockito.when(itemRepository.findById(ArgumentMatchers.anyString()))
                .thenReturn(Mono.just(itemGerado));

        StepVerifier.create(itemServiceImpl.buscarItemPeloID("1"))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    void devePermitirCriarItens() {
        var itemSalvo = ItemHelper.gerarItem();

        StepVerifier.create(itemServiceImpl.criarItem(itemSalvo))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    void devePermitirAtualizarItem() {
        var itemGerado = ItemHelper.gerarItem();
        BDDMockito.when(itemRepository.findById(itemGerado.getId()))
                .thenReturn(Mono.just(itemGerado));
        var itemSalvo = ItemHelper.gerarItemAtualizacao();

        StepVerifier.create(itemServiceImpl.atualizarItem(itemSalvo))
                .expectSubscription()
                .verifyComplete();
    }

    @Test
    void devePermitirDeleteItem() {
        var itemGerado = ItemHelper.gerarItem();
        BDDMockito.when(itemRepository.findById(itemGerado.getId()))
                .thenReturn(Mono.just(itemGerado));

        StepVerifier.create(itemServiceImpl.deleteItem(itemGerado.getId()))
                .expectSubscription()
                .verifyComplete();
    }

}