package tzin_ltda.ipcg_missoes.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.MaterialAtividadeListConverter;
import tzin_ltda.ipcg_missoes.operation.comon.ListConverter.MaterialListConverter;
import tzin_ltda.ipcg_missoes.operation.model.dto.MaterialAtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.dto.MaterialDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Atividade;
import tzin_ltda.ipcg_missoes.operation.model.entity.Material;
import tzin_ltda.ipcg_missoes.operation.model.entity.MaterialAtividade;
import tzin_ltda.ipcg_missoes.operation.model.request.MaterialAtividadeRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.AtividadeMaterialRepository;
import tzin_ltda.ipcg_missoes.operation.repository.AtividadeRepository;
import tzin_ltda.ipcg_missoes.operation.repository.MaterialRepository;

@Service
@Log4j2
public class AtividadeMaterialService {

     @Autowired
    private AtividadeMaterialRepository atividadeMaterialRepository;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private MaterialRepository materialRepository;

    public BasicResponse registrarUsoDeMaterial(MaterialAtividadeRequest request){

        try {

            Material material = this.materialRepository.findById(request.getMaterialId()).get();
            Atividade atividade = this.atividadeRepository.findById(request.getAtividadeId()).get();

            MaterialAtividade uso = new MaterialAtividade();
            int quantidadeDisponivel = material.getQuantidade();
            int quantidadeUsada = request.getQuantidadeUsada();
            if (quantidadeUsada > quantidadeDisponivel) {
                 return new BasicResponse("Quantidade usada não pode ser\n"+
                  " maior que quantidade disponivel do material (" + quantidadeDisponivel +")." , false);
                
            }
            uso.setAtividade(atividade);
            uso.setMaterial(material);
            uso.setQuantidadeUsada(quantidadeUsada);

            this.atividadeMaterialRepository.save(uso);

            return new BasicResponse("Material usado regristado com sucesso!", true);

            
        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao registrar material usado!", false);

        }

    }

    public List<MaterialAtividadeDto> listarMaterialsPresentesNaAtividade(Atividade atividade){
        List<MaterialAtividade> materiais = this.atividadeMaterialRepository.findAllByAtividade(atividade);

        return MaterialAtividadeListConverter.listagemBasica(materiais);
    }

    public List<MaterialDto> listarMateriaisNaoUsados(Long atividadeId) throws NotFoundException {

        try {
            log.info("no service.");

            List<MaterialDto>[] arrayDeListas = new List[2];

            List<Material> materiais = this.materialRepository.findAll();
            List<Material> materiaisUsados = this.atividadeMaterialRepository.findAllMaterialByAtividade(atividadeId);

            List<Material> materiaisNaoUsados = new ArrayList<>(materiais);

            if (materiaisUsados != null) {
                materiaisNaoUsados.removeAll(materiaisUsados);

            }

           

            return MaterialListConverter.listagemBasica(materiaisNaoUsados);

        } catch (Exception e) {
            // TODO: handle exception
            throw new NotFoundException("Atividade não existe");
        }

    }
    
     public BasicResponse deletarFrequencia(Long atividadeId, Long materialId){ 
        try {
            this.atividadeMaterialRepository.deletarUso(atividadeId, materialId);

            return new BasicResponse("Registro de material deletado com sucesso!", true);


            
        } catch (RuntimeException e) {
            // TODO: handle exception

            return new BasicResponse("Erro ao deletar registro de material!", false);

        }
    }
    
}
