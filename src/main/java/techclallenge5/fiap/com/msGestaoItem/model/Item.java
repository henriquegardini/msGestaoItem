package techclallenge5.fiap.com.msGestaoItem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "a id do produto não pode estar vazio.")
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