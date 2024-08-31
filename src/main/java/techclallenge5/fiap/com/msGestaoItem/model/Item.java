package techclallenge5.fiap.com.msGestaoItem.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "itens")
@Builder
public class Item {

    @Id
    private String id;

    @NotEmpty(message = "a descrição não pode estar vazia.")
    private String descricao;

    @NotNull(message = "a quantidade não pode estar vazia.")
    private Integer quantidade;

    @NotNull(message = "o preço unitário não pode estar vazio.")
    private Double precoUnitario;

    @NotNull(message = "o preço total não pode estar vazio.")
    private Double precoTotal;

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", quantidade='" + quantidade + '\'' +
                ", precoUnitario='" + precoUnitario + '\'' +
                ", precoTotal='" + precoTotal + '\'' +
                '}';
    }

}