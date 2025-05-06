package tzin_ltda.ipcg_missoes.operation.model.dto;

import java.time.LocalDate;


import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.BasicDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Cesta;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.model.request.CestaRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CestaDto extends BasicDto<Cesta, CestaRequest> {

   
    private Long id;
    private Double valor;
    private LocalDate data;
    private MembroDto membroDto;
    @Override
    public Cesta toEntity() {
        // TODO Auto-generated method stub
        Cesta cesta = new Cesta(id, valor, data, null);

        if(membroDto != null){
            cesta.setMembro(membroDto.toEntity());
        }

        return cesta;
    }
    @Override
    public CestaRequest toRequest() {
        // TODO Auto-generated method stub
        CestaRequest request = new CestaRequest(valor, data, null);

        if(membroDto != null){
            request.setMembro_id(membroDto.getId());
        }

        return request;
    }


   
    
}
