package tzin_ltda.ipcg_missoes.operation.comon.ListConverter;

import java.util.ArrayList;
import java.util.List;

import tzin_ltda.ipcg_missoes.operation.model.dto.MembroDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;

public class MembroListConverter {

    public static List<MembroDto> listagemBasica(List<Membro> membros){
        List<MembroDto> dtos = new ArrayList<>();

        for (Membro membro : membros) {
            MembroDto dto = membro.toDto();
            dtos.add(dto);
        }

        return dtos;
    }
    
}
