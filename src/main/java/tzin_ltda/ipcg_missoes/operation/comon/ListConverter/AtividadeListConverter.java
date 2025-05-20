package tzin_ltda.ipcg_missoes.operation.comon.ListConverter;

import java.util.ArrayList;
import java.util.List;

import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;

public class AtividadeListConverter {

    
    public static List<AtividadeDto> listagemBasica(List<Atividade> atividades) {
        List<AtividadeDto> dtos = new ArrayList<>();
        for (Atividade atividade : atividades) {
            dtos.add(atividade.toDto());
        }
        return dtos;
    }
    
}
