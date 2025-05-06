package tzin_ltda.ipcg_missoes.operation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tzin_ltda.ipcg_missoes.operation.model.dto.CestaDto;
import tzin_ltda.ipcg_missoes.operation.model.entity.Cesta;
import tzin_ltda.ipcg_missoes.operation.model.entity.Membro;
import tzin_ltda.ipcg_missoes.operation.model.request.CestaRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.repository.CestaRepository;
import tzin_ltda.ipcg_missoes.operation.repository.MembroRepository;

@Service
public class CestaService {

    @Autowired
    private CestaRepository cestaRepository;

    @Autowired
    private MembroRepository membroRepository;

    public List<CestaDto> listarTodos() {
        try {
            return this.listagemBasica(this.cestaRepository.findAll());
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public CestaDto buscarPorId(Long id) {
        try {
            return this.cestaRepository.findById(id).get().toDto();
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }


    public BasicResponse salvar(CestaRequest request) {

        try {

            Cesta cesta = new Cesta();
            this.persitirCesta(request, cesta);

            return new BasicResponse("Sucesso ao cadastrar cesta básica sorteada.", true);

        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao cadastrar cesta básica sorteada.", false);

        }
    }
    
    public BasicResponse editar(CestaRequest request, Long id) {

        try {

            Cesta cesta = this.cestaRepository.findById(id).get();
            this.persitirCesta(request, cesta);

            return new BasicResponse("Sucesso ao editar cesta básica sorteada.", true);

        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao editar cesta básica sorteada.", false);

        }
    }
    
    public BasicResponse deletar(Long id) {
        try {

            this.cestaRepository.deleteById(id);

            return new BasicResponse("Sucesso ao excluir registro de cesta básica.", true);

        } catch (Exception e) {
            // TODO: handle exception
            return new BasicResponse("Erro ao excluir registro de cesta básica.", false);

        }
    }
    
    private void persitirCesta(CestaRequest request, Cesta cesta) {
        Membro membro = this.membroRepository.findById(request.getMembro_id()).get();

        cesta.setData(request.getData());
        cesta.setValor(request.getValor());
        cesta.setMembro(membro);

        cesta = this.cestaRepository.save(cesta);

    }
    
    private List<CestaDto> listagemBasica(List<Cesta> cestas) {
        List<CestaDto> dtos = new ArrayList<CestaDto>();
        for (Cesta cesta : cestas) {
            dtos.add(cesta.toDto());
        }
        return dtos;
    }

    
    
}
