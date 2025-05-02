package tzin_ltda.ipcg_missoes.operation.model.request;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;


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
    private MultipartFile imagem;
    
}
