package tzin_ltda.ipcg_missoes.operation.model.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImagemAtividadeRequest {

        private MultipartFile imagem;
        private Long atividadeId;




    
}
