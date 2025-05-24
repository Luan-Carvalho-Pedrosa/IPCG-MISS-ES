package tzin_ltda.ipcg_missoes.operation.model.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaterialAtividadeRequest {

    private Long atividadeId;

    @NotNull(message = "Informe o ID do material")
    private Long materialId;

    @NotNull(message = "Informe a quantidade usada")
    private Integer quantidadeUsada;

}
