package tzin_ltda.ipcg_missoes.operation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.AtividadeListConverter;
import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.AtividadeRepository;

@Service
@Log4j2

public class AtividadeService {
     @Autowired
    private AtividadeRepository atividadeRepository;


    public BasicResponse salvar(AtividadeRequest request) {
        try {
            log.info("Salvando atividade");
            Atividade atividade = request.toEntity();
            atividadeRepository.save(atividade);
            return new BasicResponse("Atividade cadastrada com sucesso!", true);
        } catch (Exception e) {
            log.error("Erro ao salvar atividade: ", e);
            return new BasicResponse("Erro ao cadastrar atividade.", false);
        }
    }

    public BasicResponse editar(AtividadeRequest request, Long id) {
        try {
            Optional<Atividade> optionalAtividade = atividadeRepository.findById(id);
            if (optionalAtividade.isEmpty()) {
                return new BasicResponse("Atividade não encontrada.", false);
            }

            Atividade atividade = optionalAtividade.get();
            atividade.setData(request.getData());
            atividade.setRelatorio(request.getRelatorio());

            atividadeRepository.save(atividade);
            return new BasicResponse("Atividade editada com sucesso!", true);
        } catch (Exception e) {
            log.error("Erro ao editar atividade: ", e);
            return new BasicResponse("Erro ao editar atividade.", false);
        }
    }

    public AtividadeDto buscarPorId(Long id) {
        Atividade atividade = atividadeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

        return atividade.toDto();
    }

    public List<AtividadeDto> listarAtividades() {
        try {
            List<Atividade> atividades = atividadeRepository.findAll();
            return AtividadeListConverter.listagemBasica(atividades);
        } catch (Exception e) {
            log.error("Erro ao listar atividades: ", e);
            return new ArrayList<>();
        }
    }

    public BasicResponse deletar(Long id) {
        try {
            atividadeRepository.deleteById(id);
            return new BasicResponse("Atividade deletada com sucesso!", true);
        } catch (Exception e) {
            log.error("Erro ao deletar atividade: ", e);
            return new BasicResponse("Erro ao deletar atividade.", false);
        }
    }

   
    
}
