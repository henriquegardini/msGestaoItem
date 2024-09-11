package techclallenge5.fiap.com.msGestaoItem.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;
import techclallenge5.fiap.com.msGestaoItem.model.Produto;

@FeignClient(value = "produtoClient", url = "http://localhost:8086")
public interface ProdutoClient {

    @GetMapping(value = "/precos/obterPreco/{id}")
    Produto getProdutoById(@PathVariable("id") Long id);

}
