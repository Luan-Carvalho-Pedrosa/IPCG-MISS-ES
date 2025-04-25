package tzin_ltda.ipcg_missoes.operation.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicResponse {

    private String message;
    private boolean sucesso;
    
}
