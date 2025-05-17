package tzin_ltda.ipcg_missoes.operation.model.request;

import javax.validation.constraints.NotNull;

public class MaterialAtividadeRequest {
 
    @NotNull(message = "Informe o ID do material")
    private Long materialId;

    @NotNull(message = "Informe a quantidade usada")
    private Integer quantidadeUsada;
}
