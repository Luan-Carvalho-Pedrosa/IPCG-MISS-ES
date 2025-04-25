package tzin_ltda.ipcg_missoes.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.model.dto.VoluntarioDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Pessoa;
import tzin_ltda.ipcg_missoes.operation.model.entity.Voluntario;
import tzin_ltda.ipcg_missoes.operation.model.request.VoluntarioRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.PessoaRepository;
import tzin_ltda.ipcg_missoes.operation.repository.VoluntarioRepository;

@Service
@Log4j2
public class VoluntarioService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    public BasicResponse salvar(VoluntarioRequest request){

        try {
            log.info("Nos service");
            Pessoa pessoa =  new Pessoa(null, request.getNome(), request.getCpf());
            pessoa = this.pessoaRepository.save(pessoa);

            Voluntario voluntario = new Voluntario(null, request.getOcupacao(), request.getTelefone(), pessoa);
            this.voluntarioRepository.save(voluntario);

            return new BasicResponse("Voluntário cadastrado com sucesso!", true);



        } catch (RuntimeException e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao cadastrar voluntário.", false);

        }
    }

        
    public BasicResponse editar(VoluntarioRequest request, Long id){

        try {
            Voluntario voluntarioOriginal = this.voluntarioRepository.findById(id).get();
            Pessoa pessoa =  voluntarioOriginal.getPessoa();
            pessoa.setCpf(request.getCpf());
            pessoa.setNome(request.getNome());
            pessoa = this.pessoaRepository.save(pessoa);

            voluntarioOriginal.setOcupacao(request.getOcupacao());
            voluntarioOriginal.setTelefone(request.getTelefone());
            this.voluntarioRepository.save(voluntarioOriginal);

            return new BasicResponse("Voluntário editado com sucesso!", true);

        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao editar voluntário.", false);

        }

    }    

    public VoluntarioDto buscarVoluntarioPorId(Long id){
        Voluntario voluntario =  this.voluntarioRepository.findById(id).get();
        if(voluntario == null){
            throw new RuntimeException();

        }

        return voluntario.toDto();


    }

    public List<VoluntarioDto> listarVoluntarios(){
        try {
            List<Voluntario> voluntarios =  this.voluntarioRepository.findAll();

            return this.listagemBasica(voluntarios);
            

        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public BasicResponse deletarVoluntario(Long pessoaId){

        try {
            this.pessoaRepository.deleteById(pessoaId);

            return new BasicResponse("Voluntário deletado com sucesso!", true);



        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao deletar voluntário.", false);

        }
    }

    private List<VoluntarioDto> listagemBasica(List<Voluntario> voluntarios){
        List<VoluntarioDto> dtos = new ArrayList<>();

        for (Voluntario voluntario : voluntarios) {
            VoluntarioDto dto = voluntario.toDto();
            dtos.add(dto);
        }

        return dtos;
    }
    
}
