package techclallenge5.fiap.com.msGestaoItem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private Long produtoId;
    private BigDecimal precoNormal;
    private BigDecimal precoPromocional;
    private LocalDate dataInicioPromocao;
    private LocalDate dataFimPromocao;
    private BigDecimal precoAtualUnitario;

}
