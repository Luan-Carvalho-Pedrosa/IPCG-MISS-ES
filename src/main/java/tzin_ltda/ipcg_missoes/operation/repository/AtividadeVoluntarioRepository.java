package tzin_ltda.ipcg_missoes.operation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.AtividadeVoluntario;
import tzin_ltda.ipcg_missoes.operation.model.entity.Voluntario;

@Repository
public interface AtividadeVoluntarioRepository extends JpaRepository<AtividadeVoluntario, Long> {

    @Query("SELECT a FROM Atividade a WHERE a.id NOT IN " +
           "(SELECT DISTINCT av.atividade.id FROM AtividadeVoluntario av WHERE av.voluntario.id = :voluntarioId)")
    List<Atividade> findAtividadesNaoAssociadasAoVoluntario(@Param("voluntarioId") Long voluntarioId);

    @Query("SELECT DISTINCT av.atividade FROM AtividadeVoluntario av WHERE av.voluntario.id = :voluntarioId")
    List<Atividade> findAllAtividadeByVoluntario(@Param("voluntarioId") Long voluntarioId);

    @Query("SELECT DISTINCT av.voluntario FROM AtividadeVoluntario av WHERE av.atividade.id = :atividadeId")
    List<Voluntario> findAllVoluntarioByAtividade( @Param("atividadeId") Long atividadeId);

    @Modifying
    @Query("DELETE FROM AtividadeVoluntario av WHERE av.atividade.id = :atividadeId AND av.voluntario.id = :voluntarioId")
    void deletarFrequencia( @Param("atividadeId") Long atividadeId, @Param("voluntarioId") Long voluntarioId );


    
    
}
