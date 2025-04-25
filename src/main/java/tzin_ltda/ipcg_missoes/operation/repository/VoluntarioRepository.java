package tzin_ltda.ipcg_missoes.operation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tzin_ltda.ipcg_missoes.operation.model.entity.Voluntario;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

    
}
