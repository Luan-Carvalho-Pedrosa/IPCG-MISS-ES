package tzin_ltda.ipcg_missoes.operation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.model.BasicDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeRequest;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AtividadeDto extends BasicDto<Atividade, AtividadeRequest> {

    private Long id;
    private LocalDate data;
    private String relatorio;

    @Override
    public Atividade toEntity() {
        return new Atividade(id, data, relatorio);
    }

    @Override
    public AtividadeRequest toRequest() {
        AtividadeRequest request = new AtividadeRequest();
        request.setData(data);
        request.setRelatorio(relatorio);
      
        return request;
    }
}
