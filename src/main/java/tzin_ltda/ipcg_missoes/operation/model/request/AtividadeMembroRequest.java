package tzin_ltda.ipcg_missoes.operation.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AtividadeMembroRequest {

    private Long atividadeId;
    private Long membroId;
    
}
