package tzin_ltda.ipcg_missoes.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.AtividadeListConverter;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.MembroListConverter;
import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.dto.MembroDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.AtividadeMembro;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeMembroRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.AtividadeRepository;
import tzin_ltda.ipcg_missoes.operation.repository.AtividadeMembroRepository;
import tzin_ltda.ipcg_missoes.operation.repository.MembroRepository;

@Service
@Log4j2
public class AtividadeMembroService {

    @Autowired
    private AtividadeMembroRepository atividadeMembroRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private MembroRepository MembroRepository;

    public BasicResponse registrarFrequencia(AtividadeMembroRequest request){

        try {

            Membro membro = this.MembroRepository.findById(request.getMembroId()).get();
            Atividade atividade = this.atividadeRepository.findById(request.getAtividadeId()).get();

            AtividadeMembro frequencia = new AtividadeMembro();
            frequencia.setAtividade(atividade);
            frequencia.setMembro(membro);

            this.atividadeMembroRepository.save(frequencia);

            return new BasicResponse("Frequência registrada com sucesso!", true);

            
        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao registrar frequência!", false);

        }

    }

    public List<AtividadeDto>[] dividirAtividadesPorPresençaDeMembro(Long membroId) throws NotFoundException {

        try {
            log.info("no service.");
            
            List<AtividadeDto>[] arrayDeListas = new List[2];

            List<Atividade> atividades = this.atividadeRepository.findAll();
            List<Atividade> atividadesVinculadas = this.atividadeMembroRepository.findAllAtividadeByMembro(membroId);


            List<Atividade> atividadesNaoVinvuladas = new ArrayList<>(atividades);

            if(atividadesVinculadas != null){
                atividadesNaoVinvuladas.removeAll(atividadesVinculadas);

            }

            arrayDeListas[0] = AtividadeListConverter.listagemBasica(atividadesVinculadas);
            arrayDeListas[1] = AtividadeListConverter.listagemBasica(atividadesNaoVinvuladas);

        return arrayDeListas;

            
        } catch (Exception e) {
            // TODO: handle exception
            throw new NotFoundException("Membro não existe.");
        }
        
    }

     public List<AtividadeDto> listarAtividadesVinculadasAoMembro(Membro membro){
        List<Atividade> atividades = this.atividadeMembroRepository.findAllAtividadeByMembro(membro.getId());

        return AtividadeListConverter.listagemBasica(atividades);

    }

    public List<MembroDto> listarMembrosPresentesNaAtividade(Atividade atividade){
        List<Membro> Membros = this.atividadeMembroRepository.findAllMembroByAtividade(atividade.getId());

        return MembroListConverter.listagemBasica(Membros);
    }

     public List<MembroDto>[] dividirMembrosPorPresençaNaAtividade(Long atividadeId) throws NotFoundException {

        try {
            log.info("no service.");
            
            List<MembroDto>[] arrayDeListas = new List[2];

            List<Membro> membros = this.MembroRepository.findAll();
            List<Membro> membrosPresentes = this.atividadeMembroRepository.findAllMembroByAtividade(atividadeId);


            List<Membro> membrosAusentes = new ArrayList<>(membros);

            if(membrosPresentes != null){
                membrosAusentes.removeAll(membrosPresentes);

            }

            arrayDeListas[0] = MembroListConverter.listagemBasica(membrosPresentes);
            arrayDeListas[1] = MembroListConverter.listagemBasica(membrosAusentes);

        return arrayDeListas;

            
        } catch (Exception e) {
            // TODO: handle exception
            throw new NotFoundException("Atividade não existe");
        }
        
    }

    public BasicResponse deletarFrequencia(Long atividadeId, Long MembroId){ 
        try {
            this.atividadeMembroRepository.deletarFrequencia(atividadeId, MembroId);

            return new BasicResponse("Frequência deletada com sucesso!", true);


            
        } catch (RuntimeException e) {
            // TODO: handle exception

            return new BasicResponse("Erro ao deletar frequência!", false);

        }
    }
    
}
