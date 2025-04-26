package tzin_ltda.ipcg_missoes.operation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.BasicDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.model.entity.Pessoa;
import tzin_ltda.ipcg_missoes.operation.model.request.MembroRequest;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MembroDto extends BasicDto<Membro, MembroRequest> {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private Long pessoa_id;
    
    @Override
    public Membro toEntity() {
        // TODO Auto-generated method stub
        Pessoa pessoa = new Pessoa(pessoa_id, nome, cpf);
        Membro membro = new Membro(id, telefone, pessoa);

        return membro;

    }

    @Override
    public MembroRequest toRequest() {
        // TODO Auto-generated method stub
        MembroRequest membroRequest = new MembroRequest(nome, cpf, telefone);

        return membroRequest;
    }
    
}
