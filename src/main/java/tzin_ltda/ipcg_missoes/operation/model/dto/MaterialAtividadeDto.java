package tzin_ltda.ipcg_missoes.operation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.request.MaterialAtividadeRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MaterialAtividadeDto {

    private Long materialId;
    private Integer quantidadeUsada;

    public MaterialAtividadeRequest toRequest() {
        return new MaterialAtividadeRequest();
    }
}

