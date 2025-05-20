package tzin_ltda.ipcg_missoes.operation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.BasicDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.AtividadeVoluntario;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeVoluntarioRequest;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AtividadeVoluntarioDto {

    private Long id;
    private AtividadeDto atividadeDto;
    private VoluntarioDto voluntarioDto;


    public AtividadeVoluntarioRequest toRequest(){
        AtividadeVoluntarioRequest request = new AtividadeVoluntarioRequest();
        if(atividadeDto != null){
            request.setAtividadeId(atividadeDto.getId());
        }
        if(voluntarioDto != null){
            request.setVoluntarioId(voluntarioDto.getId());
        }
        return request;

    }

    public AtividadeVoluntario toEntity(){
        AtividadeVoluntario atividadeVoluntario = new AtividadeVoluntario();
        if(atividadeDto != null){
            atividadeVoluntario.setAtividade(atividadeDto.toEntity());
        }
        if(voluntarioDto != null){
            atividadeVoluntario.setVoluntario(voluntarioDto.toEntity());
        }
        return atividadeVoluntario;
        
    }


    
}
