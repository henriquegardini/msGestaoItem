package techclallenge5.fiap.com.msGestaoItem.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.service.ItemService;
import techclallenge5.fiap.com.msGestaoItem.utils.ItemHelper;

class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;
    @Mock
    private ItemService itemService;

    private final Item itemMock = ItemHelper.gerarItem();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        BDDMockito.when(itemService.buscarItens())
                .thenReturn(Flux.just(itemMock));

        BDDMockito.when(itemService.buscarItemPeloID(itemMock.getId()))
                .thenReturn(Mono.just(itemMock));

        BDDMockito.when(itemService.criarItem(itemMock))
                .thenReturn(Mono.just(itemMock));

        BDDMockito.when(itemService.atualizarItem(itemMock))
                .thenReturn(Mono.just(itemMock));

        BDDMockito.when(itemService.deleteItem(itemMock.getId()))
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
        StepVerifier.create(itemController.atualizarItem(itemMock))
                .expectSubscription()
                .expectNext(itemMock)
                .verifyComplete();
    }

    @Test
    public void deveDeletarItemComSucesso() {
        StepVerifier.create(itemController.deletarItem(itemMock.getId()))
                .expectSubscription()
                .verifyComplete();
    }

}