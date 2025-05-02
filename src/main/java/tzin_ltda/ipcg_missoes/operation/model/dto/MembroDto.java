package tzin_ltda.ipcg_missoes.operation.model.dto;

import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tzin_ltda.ipcg_missoes.operation.comon.ByteArrayMultipartFile;
import tzin_ltda.ipcg_missoes.operation.model.BasicDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.model.entity.Pessoa;
import tzin_ltda.ipcg_missoes.operation.model.request.MembroRequest;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MembroDto extends BasicDto<Membro, MembroRequest> {

    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private byte[] imagem;
    private Long pessoa_id;
    
    @Override
    public Membro toEntity() {
        // TODO Auto-generated method stub
        Pessoa pessoa = new Pessoa(pessoa_id, nome, cpf);
        Membro membro = new Membro(id, telefone, imagem,  pessoa);

        return membro;

    }

    @Override
    public MembroRequest toRequest() {
        // TODO Auto-generated method stub
        MembroRequest membroRequest = new MembroRequest(nome, cpf, telefone, null);

        if (imagem != null) {
            MultipartFile imagemNova = new ByteArrayMultipartFile(
                    imagem,
                    "imagem-" + nome,
                    nome + "-imagem.png",
                    "image/png");
            membroRequest.setImagem(imagemNova);
        }

        return membroRequest;
    }

    public String getImagemBase64() {
        String imagemBase64 = null;
        if (imagem != null ) {
             imagemBase64 = Base64.getEncoder().encodeToString(imagem);
        }
        return imagemBase64;
    }
    
    
    
}
