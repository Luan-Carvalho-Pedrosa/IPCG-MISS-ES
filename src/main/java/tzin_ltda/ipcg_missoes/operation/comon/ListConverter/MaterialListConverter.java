package tzin_ltda.ipcg_missoes.operation.comon.ListConverter;

import java.util.ArrayList;
import java.util.List;

import tzin_ltda.ipcg_missoes.operation.model.dto.MaterialDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Material;

public class MaterialListConverter {

    
    public static List<MaterialDto> listagemBasica(List<Material> materiais) {
        List<MaterialDto> dtos = new ArrayList<>();
        for (Material material : materiais) {
            dtos.add(material.toDto());
        }
        return dtos;
    }
    
}
