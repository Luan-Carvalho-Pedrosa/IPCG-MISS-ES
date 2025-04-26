package tzin_ltda.ipcg_missoes.operation.model.request;

import javax.validation.constraints.NotBlank;

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
public class MembroRequest {

    @NotBlank(message = "Insira o nome")
    private String nome;
    @NotBlank(message = "Insira o cpf")
    private String cpf;
    private String telefone;
    
}
