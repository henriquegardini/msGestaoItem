package techclallenge5.fiap.com.msGestaoItem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@Table(name = "item")
public class Item {

    @Id
    private String id;

    @NotNull(message = "a id do produto não pode estar vazio.")
    private Long idProduto;

    @NotEmpty(message = "a descrição não pode estar vazia.")
    private String descricao;

    @NotNull(message = "a quantidade não pode estar vazia.")
    private BigDecimal quantidade;

    private BigDecimal precoTotal;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", precoTotal='" + precoTotal + '\'' +
                '}';
    }

}