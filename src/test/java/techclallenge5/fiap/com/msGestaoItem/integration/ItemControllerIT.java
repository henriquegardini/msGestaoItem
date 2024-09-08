package techclallenge5.fiap.com.msGestaoItem.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.model.Item;
import techclallenge5.fiap.com.msGestaoItem.repository.ItemRepository;
import techclallenge5.fiap.com.msGestaoItem.utils.ItemHelper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ItemControllerIT {

    @MockBean
    private ItemRepository itemRepository;
    @Autowired
    private WebTestClient client;

    private final Item itemMock = ItemHelper.gerarItem();
    private final Item itemNaoExistenteMock = ItemHelper.gerarItemNaoExistente();

    @BeforeEach
    public void setUp() {
        BDDMockito.when(itemRepository.findById(itemMock.getId().toString())).thenReturn(Mono.just(itemMock));
        BDDMockito.when(itemRepository.save(itemMock)).thenReturn(Mono.just(itemMock));
    }

    @Test
    public void devebuscarItensComSucesso() {
        client.get()
                .uri("/item")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void deveBuscarItemPeloIDComSucesso() {
        client.get()
                .uri("/item/{idItem}", itemMock.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody(itemMock.getClass());
    }

    @Test
    public void deveCriarItemComSucesso() {
        client.post()
                .uri("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(itemMock))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Item.class);
    }

    @Test
    public void deveAtualizarItemComSucesso() {
        client.patch()
                .uri("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(itemMock))
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    public void deveAtualizarItemSemSucesso() {
        client.patch()
                .uri("/item")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(itemNaoExistenteMock))
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    public void deveDeletarItemComSucesso() {
        client.delete()
                .uri("/item/{idItem}", itemMock.getId())
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    public void deveDeletarItemSemSucesso() {
        client.delete()
                .uri("/item/{idItem}", itemNaoExistenteMock.getId())
                .exchange()
                .expectStatus().is5xxServerError();
    }

}