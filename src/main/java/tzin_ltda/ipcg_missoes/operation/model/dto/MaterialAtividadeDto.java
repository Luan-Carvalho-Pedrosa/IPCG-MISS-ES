package tzin_ltda.ipcg_missoes.operation.model.dto;

import liquibase.pro.packaged.q;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.entity.MaterialAtividade;
import tzin_ltda.ipcg_missoes.operation.model.request.MaterialAtividadeRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MaterialAtividadeDto {

    private Long id;
    private MaterialDto material;
    private AtividadeDto atividade;
    private Integer quantidadeUsada;

    public MaterialAtividadeRequest toRequest() {
        MaterialAtividadeRequest request = new MaterialAtividadeRequest();
        request.setQuantidadeUsada(quantidadeUsada);
        if (material != null) {
            request.setMaterialId(material.getId());
        }

        if (atividade != null) {
            request.setAtividadeId(atividade.getId());
        }

        return request;
    }
    
    public MaterialAtividade toEntity() {

        MaterialAtividade atividadeMaterial = new MaterialAtividade();
        atividadeMaterial.setId(id);
        material.setQuantidade(quantidadeUsada);
        if(atividade != null){
            atividadeMaterial.setAtividade(atividade.toEntity());
        }
        if (material != null) {
            atividadeMaterial.setMaterial(material.toEntity());
        }
        
        return atividadeMaterial;
        
    }
}

