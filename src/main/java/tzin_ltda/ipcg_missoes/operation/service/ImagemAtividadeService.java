package tzin_ltda.ipcg_missoes.operation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.ImagemAtividadeListConverter;
import tzin_ltda.ipcg_missoes.operation.model.dto.ImagemAtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.ImagemAtividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.model.request.ImagemAtividadeRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.AtividadeRepository;
import tzin_ltda.ipcg_missoes.operation.repository.ImagemAtividadeRepository;
import tzin_ltda.ipcg_missoes.operation.validation.exceptions.NotPngException;

@Service
@Log4j2
public class ImagemAtividadeService {

    @Autowired
    private ImagemAtividadeRepository imagemAtividadeRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    public BasicResponse registrarImagem(ImagemAtividadeRequest request) {

        try {

            Atividade atividade = this.atividadeRepository.findById(request.getAtividadeId()).get();

            ImagemAtividade imagemAtividade = new ImagemAtividade();
            imagemAtividade.setAtividade(atividade);

            this.salvarImagem(imagemAtividade, request.getImagem());

            this.imagemAtividadeRepository.save(imagemAtividade);

            return new BasicResponse("Imagem registrada com sucesso!", true);

        }
        catch (MaxUploadSizeExceededException e) {
            return new BasicResponse(" Erro: O arquivo excede o tamanho máximo de 1MB.", false);

        } 
        
        catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao registrar imagem.", false);

        }

    }
    
    public List<ImagemAtividadeDto> listarImagensPorAtividade(Atividade atividade) {

        return ImagemAtividadeListConverter
                .listagemBasica(this.imagemAtividadeRepository.findAllByAtividade(atividade));

    }

    private void salvarImagem(ImagemAtividade imagemAtividade, MultipartFile imagem)
            throws NotPngException, IOException {
        if (imagem != null) {
            if (!imagem.isEmpty()) {

                if (!imagem.getContentType().equals("image/png")) {
                    throw new NotPngException("Apenas arquivos PNG são permitidos");
                } else {
                    log.info("Imagem valida");
                    imagemAtividade.setImagem(imagem.getBytes());

                }

            } else {
            }
        }
    }
    
    public BasicResponse deletarImagem(Long id) {
        
        try {

            this.imagemAtividadeRepository.deleteById(id);

            return new BasicResponse("Imagem deletada com sucesso!", true);

            
        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao deletar imagem.", false);

        }
    }
    
    
}
