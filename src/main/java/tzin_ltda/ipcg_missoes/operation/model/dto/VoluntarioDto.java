package tzin_ltda.ipcg_missoes.operation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.entity.Pessoa;
import tzin_ltda.ipcg_missoes.operation.model.entity.Voluntario;
import tzin_ltda.ipcg_missoes.operation.model.request.VoluntarioRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoluntarioDto {

    private Long id;
    private String nome;
    private String cpf;
    private String ocupacao;
    private String telefone;
    private Long pessoa_id;

    public Voluntario toEntity() {
        Pessoa pessoa = new Pessoa(pessoa_id, nome, cpf);
        Voluntario voluntario = new Voluntario(id, ocupacao, telefone, pessoa);

        return voluntario;
    }

    public VoluntarioRequest toRequest(){
        VoluntarioRequest request = new VoluntarioRequest(nome, cpf, telefone, ocupacao);

        return request;
    }
    
}
