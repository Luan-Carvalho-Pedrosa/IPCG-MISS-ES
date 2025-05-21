package tzin_ltda.ipcg_missoes.operation.comon.ListConverter;

import java.util.ArrayList;
import java.util.List;

import tzin_ltda.ipcg_missoes.operation.model.dto.ImagemAtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.ImagemAtividade;

public class ImagemAtividadeListConverter {

    public static List<ImagemAtividadeDto> listagemBasica(List<ImagemAtividade> imagemAtividades){
        List<ImagemAtividadeDto> dtos = new ArrayList<>();

        for (ImagemAtividade imagemAtividade : imagemAtividades) {
            ImagemAtividadeDto dto = imagemAtividade.toDto();
            dtos.add(dto);
        }

        return dtos;
    }
    
}
