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

    public BigDecimal getPrecoAtualUnitario() {
        return precoAtualUnitario;
    }

    public void setPrecoAtualUnitario(BigDecimal precoAtualUnitario) {
        this.precoAtualUnitario = precoAtualUnitario;
    }

    public LocalDate getDataFimPromocao() {
        return dataFimPromocao;
    }

    public void setDataFimPromocao(LocalDate dataFimPromocao) {
        this.dataFimPromocao = dataFimPromocao;
    }

    public LocalDate getDataInicioPromocao() {
        return dataInicioPromocao;
    }

    public void setDataInicioPromocao(LocalDate dataInicioPromocao) {
        this.dataInicioPromocao = dataInicioPromocao;
    }

    public BigDecimal getPrecoPromocional() {
        return precoPromocional;
    }

    public void setPrecoPromocional(BigDecimal precoPromocional) {
        this.precoPromocional = precoPromocional;
    }

    public BigDecimal getPrecoNormal() {
        return precoNormal;
    }

    public void setPrecoNormal(BigDecimal precoNormal) {
        this.precoNormal = precoNormal;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    private Long produtoId;
    private BigDecimal precoNormal;
    private BigDecimal precoPromocional;
    private LocalDate dataInicioPromocao;
    private LocalDate dataFimPromocao;
    private BigDecimal precoAtualUnitario;

}
