package tzin_ltda.ipcg_missoes.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.AtividadeListConverter;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.VoluntarioListConverter;
import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.dto.VoluntarioDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.AtividadeVoluntario;
import tzin_ltda.ipcg_missoes.operation.model.entity.Voluntario;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeVoluntarioRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.AtividadeRepository;
import tzin_ltda.ipcg_missoes.operation.repository.AtividadeVoluntarioRepository;
import tzin_ltda.ipcg_missoes.operation.repository.VoluntarioRepository;

@Service
@Log4j2
public class AtividadeVoluntarioService {

    @Autowired
    private AtividadeVoluntarioRepository atividadeVoluntarioRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    public BasicResponse registrarFrequencia(AtividadeVoluntarioRequest request){

        try {

            Voluntario voluntario = this.voluntarioRepository.findById(request.getVoluntarioId()).get();
            Atividade atividade = this.atividadeRepository.findById(request.getAtividadeId()).get();

            AtividadeVoluntario frequencia = new AtividadeVoluntario();
            frequencia.setAtividade(atividade);
            frequencia.setVoluntario(voluntario);

            this.atividadeVoluntarioRepository.save(frequencia);

            return new BasicResponse("Frequência registrada com sucesso!", true);

            
        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao registrar frequência!", false);

        }

    }

    public List<AtividadeDto>[] dividirAtividadesPorPresençaDeVoluntario(Long voluntarioId) throws NotFoundException {

        try {
            log.info("no service.");
            
            List<AtividadeDto>[] arrayDeListas = new List[2];

            List<Atividade> atividades = this.atividadeRepository.findAll();
            List<Atividade> atividadesVinculadas = this.atividadeVoluntarioRepository.findAllAtividadeByVoluntario(voluntarioId);


            List<Atividade> atividadesNaoVinvuladas = new ArrayList<>(atividades);

            if(atividadesVinculadas != null){
                atividadesNaoVinvuladas.removeAll(atividadesVinculadas);

            }

            arrayDeListas[0] = AtividadeListConverter.listagemBasica(atividadesVinculadas);
            arrayDeListas[1] = AtividadeListConverter.listagemBasica(atividadesNaoVinvuladas);

        return arrayDeListas;

            
        } catch (Exception e) {
            // TODO: handle exception
            throw new NotFoundException("Voluntário não existe");
        }
        
    }

     public List<AtividadeDto> listarAtividadesVinculadasAoVoluntario(Voluntario voluntario){
        List<Atividade> atividades = this.atividadeVoluntarioRepository.findAllAtividadeByVoluntario(voluntario.getId());

        return AtividadeListConverter.listagemBasica(atividades);

    }

    public List<VoluntarioDto> listarVoluntariosPresentesNaAtividade(Atividade atividade){
        List<Voluntario> voluntarios = this.atividadeVoluntarioRepository.findAllVoluntarioByAtividade(atividade.getId());

        return VoluntarioListConverter.listagemBasica(voluntarios);
    }

     public List<VoluntarioDto>[] dividirVoluntariosPorPresençaNaAtividade(Long atividadeId) throws NotFoundException {

        try {
            log.info("no service.");
            
            List<VoluntarioDto>[] arrayDeListas = new List[2];

            List<Voluntario> voluntarios = this.voluntarioRepository.findAll();
            List<Voluntario> voluntariosPresentes = this.atividadeVoluntarioRepository.findAllVoluntarioByAtividade(atividadeId);


            List<Voluntario> voluntariosAusentes = new ArrayList<>(voluntarios);

            if(voluntariosPresentes != null){
                voluntariosAusentes.removeAll(voluntariosPresentes);

            }

            arrayDeListas[0] = VoluntarioListConverter.listagemBasica(voluntariosPresentes);
            arrayDeListas[1] = VoluntarioListConverter.listagemBasica(voluntariosAusentes);

        return arrayDeListas;

            
        } catch (Exception e) {
            // TODO: handle exception
            throw new NotFoundException("Atividade não existe");
        }
        
    }

    public BasicResponse deletarFrequencia(Long atividadeId, Long voluntarioId){ 
        try {
            this.atividadeVoluntarioRepository.deletarFrequencia(atividadeId, voluntarioId);

            return new BasicResponse("Frequência deletada com sucesso!", true);


            
        } catch (RuntimeException e) {
            // TODO: handle exception

            return new BasicResponse("Erro ao deletar frequência!", false);

        }
    }
    
}
