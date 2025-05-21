package tzin_ltda.ipcg_missoes.operation.comon;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.validation.exceptions.NotPngException;

public class ImageConverter {

    public static String getImagemBase64(byte[] imagem) {
        String imagemBase64 = null;
        if (imagem != null) {
            imagemBase64 = Base64.getEncoder().encodeToString(imagem);
        }
        return imagemBase64;
    }
    
    public static void salvarImagem(Membro membro, MultipartFile imagem) throws NotPngException,  IOException {
        if (imagem != null) {
            if (!imagem.isEmpty()) {

                if (!imagem.getContentType().equals("image/png")) {
                    throw new NotPngException("Apenas arquivos PNG são permitidos");
                }
                else{
                    membro.setImagem(imagem.getBytes());

                }
            
            }
            else {
            }
        }
    }
    
}
