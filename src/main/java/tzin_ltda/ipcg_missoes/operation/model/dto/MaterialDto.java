package tzin_ltda.ipcg_missoes.operation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.BasicDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Material;
import tzin_ltda.ipcg_missoes.operation.model.request.MaterialRequest;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MaterialDto extends BasicDto<Material, MaterialRequest> {

    private Long id;
    private String nome;
    private String descricao;
    private Integer quantidade;

    @Override
    public Material toEntity() {
        return new Material(id, nome, descricao, quantidade);
    }

    @Override
    public MaterialRequest toRequest() {
        MaterialRequest request = new MaterialRequest();
        request.setNome(nome);
        request.setDescricao(descricao);
        request.setQuantidade(quantidade);
      
        return request;
    }
}
