package tzin_ltda.ipcg_missoes.operation.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.model.dto.MembroDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.model.entity.Pessoa;
import tzin_ltda.ipcg_missoes.operation.model.request.MembroRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.MembroRepository;
import tzin_ltda.ipcg_missoes.operation.repository.PessoaRepository;
import tzin_ltda.ipcg_missoes.operation.validation.exceptions.NotPngException;

@Service
@Log4j2
public class MembroService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MembroRepository membroRepository;

    public List<MembroDto> listarTodos() {
        try {
            return this.listagemBasica(this.membroRepository.findAll());

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public MembroDto buscarPorId(Long id) {
        try {
            return this.membroRepository.findById(id).get().toDto();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public BasicResponse salvar(MembroRequest request) {
        try {

            Pessoa pessoa = new Pessoa(null, request.getNome(), request.getCpf());
            pessoa = this.pessoaRepository.save(pessoa);

            Membro membro = new Membro(null, request.getTelefone(), null, pessoa);

            MultipartFile imagem = request.getImagem();
            this.salvarImagem(membro, imagem);

            membro = this.membroRepository.save(membro);

            return new BasicResponse("Membro cadastrado com sucesso!", true);

        }
        catch (MaxUploadSizeExceededException e) {
            return new BasicResponse(" Erro: O arquivo de imagem excede o tamanho máximo de 1MB.", false);

        } 
        catch (Exception e) {
            // TODO: handle exception

            return new BasicResponse("Erro ao cadastrar membro.", false);

        }

        
    }
    
    public BasicResponse editar(MembroRequest request, Long id) {
        try {

            Membro membro = this.membroRepository.findById(id).get();
            Pessoa pessoa = membro.getPessoa();

            pessoa.setNome(request.getNome());
            pessoa.setCpf(request.getCpf());

            pessoa = pessoaRepository.save(pessoa);

            membro.setTelefone(request.getTelefone());
            MultipartFile imagem = request.getImagem();

            this.salvarImagem(membro, imagem);

            membro = this.membroRepository.save(membro);

            return new BasicResponse("Membro editado com sucesso!", true);

        } catch (Exception e) {
            // TODO: handle exception

            return new BasicResponse("Erro ao editar membro!", true);

        }
    }
    
    public BasicResponse deletar(Long id) {

        try {
            Pessoa pessoa = this.membroRepository.findById(id).get().getPessoa();
            this.pessoaRepository.deleteById(pessoa.getId());

            return new BasicResponse("Membro deletado com sucesso!", true);



        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Membro ao deletar voluntário.", false);

        }
        
    }

    private List<MembroDto> listagemBasica(List<Membro> membros) {

        log.info(membros.toString());
        List<MembroDto> dtos = new ArrayList<MembroDto>();

        for (Membro membro : membros) {
            log.info(membro.toString());
            MembroDto dto = membro.toDto();
            dtos.add(dto);
        }

        return dtos;

    }

    
    private void salvarImagem(Membro membro, MultipartFile imagem) throws NotPngException,  IOException {
        if (imagem != null) {
            if (!imagem.isEmpty()) {

                if (!imagem.getContentType().equals("image/png")) {
                    throw new NotPngException("Apenas arquivos PNG são permitidos");
                }
                else{
                    log.info("Imagem valida");
                    membro.setImagem(imagem.getBytes());

                }
            
            }
            else {
            }
        }
    }

    
    
   
    
}
