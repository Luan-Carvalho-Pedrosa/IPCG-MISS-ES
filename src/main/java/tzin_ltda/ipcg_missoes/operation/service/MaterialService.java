package tzin_ltda.ipcg_missoes.operation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.MaterialListConverter;
import tzin_ltda.ipcg_missoes.operation.model.dto.MaterialDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Material;
import tzin_ltda.ipcg_missoes.operation.model.request.MaterialRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.MaterialRepository;

@Service
@Log4j2

public class MaterialService {
     @Autowired
    private MaterialRepository materialRepository;


    public BasicResponse salvar(MaterialRequest request) {
        try {
            log.info("Salvando material");
            Material material = request.toEntity();
            materialRepository.save(material);
            return new BasicResponse("Material cadastrado com sucesso!", true);
        } catch (Exception e) {
            log.error("Erro ao salvar material: ", e);
            return new BasicResponse("Erro ao cadastrar material.", false);
        }
    }

    public BasicResponse editar(MaterialRequest request, Long id) {
        try {
            Optional<Material> optionalMaterial = materialRepository.findById(id);
            if (optionalMaterial.isEmpty()) {
                return new BasicResponse("Material não encontrado.", false);
            }

            Material material = optionalMaterial.get();
            material.setNome(request.getNome());
            material.setDescricao(request.getDescricao());
            material.setQuantidade(request.getQuantidade());

            materialRepository.save(material);
            return new BasicResponse("Material editado com sucesso!", true);
        } catch (Exception e) {
            log.error("Erro ao editar material: ", e);
            return new BasicResponse("Erro ao editar material.", false);
        }
    }

    public MaterialDto buscarPorId(Long id) {
        Material material = materialRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Material não encontrado"));

        return material.toDto();
    }

    public List<MaterialDto> listarMateriais() {
        try {
            List<Material> materiais = materialRepository.findAll();
            return MaterialListConverter.listagemBasica(materiais);
        } catch (Exception e) {
            log.error("Erro ao listar materiais: ", e);
            return new ArrayList<>();
        }
    }

    public BasicResponse deletar(Long id) {
        try {
            materialRepository.deleteById(id);
            return new BasicResponse("Material deletado com sucesso!", true);
        } catch (Exception e) {
            log.error("Erro ao deletar material: ", e);
            return new BasicResponse("Erro ao deletar material.", false);
        }
    }

   
    
}
