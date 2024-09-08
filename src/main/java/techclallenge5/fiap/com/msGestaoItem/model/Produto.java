package techclallenge5.fiap.com.msGestaoItem.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto {

    private Long produtoId;
    private BigDecimal precoNormal;
    private BigDecimal precoPromocional;
    private LocalDate dataInicioPromocao;
    private LocalDate dataFimPromocao;
    private BigDecimal precoAtualUnitario;

}
