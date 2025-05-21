package tzin_ltda.ipcg_missoes.operation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.ImagemAtividade;

@Repository
public interface ImagemAtividadeRepository extends JpaRepository<ImagemAtividade, Long> {

    List<ImagemAtividade> findAllByAtividade(Atividade atividade);
    
}
