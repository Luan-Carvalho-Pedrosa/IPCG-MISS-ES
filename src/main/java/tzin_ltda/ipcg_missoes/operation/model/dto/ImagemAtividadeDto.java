package tzin_ltda.ipcg_missoes.operation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.comon.ImageConverter;
import tzin_ltda.ipcg_missoes.operation.model.entity.ImagemAtividade;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImagemAtividadeDto {

    private Long id;
    private byte[] imagem;
    private AtividadeDto atividadeDto;

    public ImagemAtividade toEntity() {

        ImagemAtividade imagemAtividade = new ImagemAtividade(id, imagem, null);

        if (atividadeDto != null) {
            imagemAtividade.setAtividade(atividadeDto.toEntity());
        }

        return imagemAtividade;

    }
    
    public String getImagemBase64() {
       
        return ImageConverter.getImagemBase64(imagem);
    }
    
}
