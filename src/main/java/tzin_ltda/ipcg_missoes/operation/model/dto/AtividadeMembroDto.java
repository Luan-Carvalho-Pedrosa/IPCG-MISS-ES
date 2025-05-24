package tzin_ltda.ipcg_missoes.operation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.entity.AtividadeMembro;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeMembroRequest;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AtividadeMembroDto {

    private Long id;
    private AtividadeDto atividadeDto;
    private MembroDto MembroDto;


    public AtividadeMembroRequest toRequest(){
        AtividadeMembroRequest request = new AtividadeMembroRequest();
        if(atividadeDto != null){
            request.setAtividadeId(atividadeDto.getId());
        }
        if(MembroDto != null){
            request.setMembroId(MembroDto.getId());
        }
        return request;

    }

    public AtividadeMembro toEntity(){
        AtividadeMembro atividadeMembro = new AtividadeMembro();
        atividadeMembro.setId(id);
        if(atividadeDto != null){
            atividadeMembro.setAtividade(atividadeDto.toEntity());
        }
        if(MembroDto != null){
            atividadeMembro.setMembro(MembroDto.toEntity());
        }
        return atividadeMembro;
        
    }
    
}
