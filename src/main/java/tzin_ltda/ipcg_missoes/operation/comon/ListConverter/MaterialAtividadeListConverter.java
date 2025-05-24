package tzin_ltda.ipcg_missoes.operation.comon.ListConverter;

import java.util.ArrayList;
import java.util.List;

import tzin_ltda.ipcg_missoes.operation.model.dto.MaterialAtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.MaterialAtividade;

public class MaterialAtividadeListConverter {
    
    public static List<MaterialAtividadeDto> listagemBasica(List<MaterialAtividade> lista){
        List<MaterialAtividadeDto> dtos = new ArrayList<>();

        for (MaterialAtividade materialAtividade : lista) {
            MaterialAtividadeDto dto = materialAtividade.toDto();
            dtos.add(dto);
        }

        return dtos;
    }
}
