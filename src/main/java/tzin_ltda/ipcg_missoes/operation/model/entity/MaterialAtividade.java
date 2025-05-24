package tzin_ltda.ipcg_missoes.operation.model.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.dto.MaterialAtividadeDto;

@Entity
@Table(name = "material_atividade")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaterialAtividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidadeUsada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "atividade_id", nullable = false)
    private Atividade atividade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

     public MaterialAtividadeDto toDto(){
        MaterialAtividadeDto dto = new MaterialAtividadeDto(id, null, null, quantidadeUsada);
        if(atividade != null){
            dto.setAtividade(atividade.toDto());
        }
        if(material != null){
            dto.setMaterial(material.toDto());
        }

        return dto;

    }

   
}

