package tzin_ltda.ipcg_missoes.operation.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "material_atividade")

public class MaterialAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long materialId;
    private Integer quantidadeUsada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    public MaterialAtividade(Long materialId) {
        this.materialId = materialId;
    }

    public MaterialAtividade(Long id, Long materialId, Integer quantidadeUsada) {
        this.id = id;
        this.materialId = materialId;
        this.quantidadeUsada = quantidadeUsada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public Integer getQuantidadeUsada() {
        return quantidadeUsada;
    }

    public void setQuantidadeUsada(Integer quantidadeUsada) {
        this.quantidadeUsada = quantidadeUsada;
    }

    
    public Atividade getAtividade() {
        return atividade;
    }

    public void setAtividade(Atividade atividade) {
        this.atividade = atividade;
    }
}

