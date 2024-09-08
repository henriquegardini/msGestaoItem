package techclallenge5.fiap.com.msGestaoItem.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.service.ItemServiceImpl;
import techclallenge5.fiap.com.msGestaoItem.utils.ItemHelper;

class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;
    @Mock
    private ItemServiceImpl itemServiceImpl;

    private final Item itemMock = ItemHelper.gerarItem();
    private final Item itemAtualizacaoMock = ItemHelper.gerarItemAtualizacao();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        BDDMockito.when(itemServiceImpl.buscarItens())
                .thenReturn(Flux.just(itemMock));

        BDDMockito.when(itemServiceImpl.buscarItemPeloID(ArgumentMatchers.anyLong()))
                .thenReturn(Mono.just(itemMock));

        BDDMockito.when(itemServiceImpl.criarItem(itemMock))
                .thenReturn(Mono.just(itemMock));

        BDDMockito.when(itemServiceImpl.atualizarItem(itemAtualizacaoMock))
                .thenReturn(Mono.just(itemAtualizacaoMock));

        BDDMockito.when(itemServiceImpl.deleteItem(1L))
                .thenReturn(Mono.empty());

    }

    @Test
    public void deveBuscarItensComSucesso() {
        StepVerifier.create(itemController.buscarItens())
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    public void devebuscarItemPeloIDComSucesso() {
        StepVerifier.create(itemController.buscarItemPeloID(itemMock.getId()))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    public void deveCriarItemComSucesso() {
        StepVerifier.create(itemController.criarItem(itemMock))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    public void deveAtualizarItemComSucesso() {
        StepVerifier.create(itemController.atualizarItem(itemAtualizacaoMock))
                .expectSubscription()
                .expectNext(itemAtualizacaoMock)
                .verifyComplete();
    }

    @Test
    public void deveDeletarItemComSucesso() {
        StepVerifier.create(itemController.deletarItem(itemMock.getId()))
                .expectSubscription()
                .verifyComplete();
    }

}