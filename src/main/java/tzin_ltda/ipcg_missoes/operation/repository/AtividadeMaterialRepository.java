package tzin_ltda.ipcg_missoes.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.Material;
import tzin_ltda.ipcg_missoes.operation.model.entity.MaterialAtividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.Material;

import java.util.List;


@Repository
public interface AtividadeMaterialRepository extends JpaRepository<MaterialAtividade, Long> {

     @Query("SELECT DISTINCT am.material FROM MaterialAtividade am WHERE am.atividade.id = :atividadeId")
    List<Material> findAllMaterialByAtividade( @Param("atividadeId") Long atividadeId);

    @Modifying
    @Query("DELETE FROM MaterialAtividade am WHERE am.atividade.id = :atividadeId AND am.material.id = :materialId")
    void deletarUso( @Param("atividadeId") Long atividadeId, @Param("materialId") Long MaterialId );
    

    List<MaterialAtividade> findAllByAtividade(Atividade atividade);
    
}
