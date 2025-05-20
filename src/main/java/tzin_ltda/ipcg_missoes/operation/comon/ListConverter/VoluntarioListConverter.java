package tzin_ltda.ipcg_missoes.operation.comon.ListConverter;

import java.util.ArrayList;
import java.util.List;

import tzin_ltda.ipcg_missoes.operation.model.dto.VoluntarioDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Voluntario;

public class VoluntarioListConverter {

     public static List<VoluntarioDto> listagemBasica(List<Voluntario> voluntarios){
        List<VoluntarioDto> dtos = new ArrayList<>();

        for (Voluntario voluntario : voluntarios) {
            VoluntarioDto dto = voluntario.toDto();
            dtos.add(dto);
        }

        return dtos;
    }
    
}
