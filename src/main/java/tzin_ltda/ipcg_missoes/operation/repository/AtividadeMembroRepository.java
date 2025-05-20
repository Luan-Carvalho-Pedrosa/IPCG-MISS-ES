package tzin_ltda.ipcg_missoes.operation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.AtividadeMembro;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;

@Repository
public interface AtividadeMembroRepository extends JpaRepository<AtividadeMembro, Long> {

    @Query("SELECT DISTINCT av.atividade FROM AtividadeMembro av WHERE av.membro.id = :membroId")
    List<Atividade> findAllAtividadeByMembro(@Param("membroId") Long membroId);

    @Query("SELECT DISTINCT av.membro FROM AtividadeMembro av WHERE av.atividade.id = :atividadeId")
    List<Membro> findAllMembroByAtividade( @Param("atividadeId") Long atividadeId);

    @Modifying
    @Query("DELETE FROM AtividadeMembro av WHERE av.atividade.id = :atividadeId AND av.membro.id = :membroId")
    void deletarFrequencia( @Param("atividadeId") Long atividadeId, @Param("membroId") Long membroId );
    
}
