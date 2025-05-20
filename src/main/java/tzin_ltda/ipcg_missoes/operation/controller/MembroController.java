package tzin_ltda.ipcg_missoes.operation.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javassist.NotFoundException;
import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.dto.MembroDto;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeMembroRequest;
import tzin_ltda.ipcg_missoes.operation.model.request.MembroRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.service.AtividadeMembroService;
import tzin_ltda.ipcg_missoes.operation.service.MembroService;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("membros")
@Log4j2
public class MembroController {

    @Autowired
    private MembroService membroService;

    @Autowired
    private AtividadeMembroService frequenciaService;

    @GetMapping("/")
    public String listar(Model model) {

        List<MembroDto> membros = this.membroService.listarTodos();

        model.addAttribute("membros", membros);

        return  "operational/membros/listar";

    }

    @GetMapping("/incluir")
    public String incluir(Model model) {

        MembroRequest membroRequest = new MembroRequest();

        model.addAttribute("membro", membroRequest);
        model.addAttribute("edit", false);

        return "operational/membros/cadastrar";

    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Long id) {

        MembroDto membroDto = this.membroService.buscarPorId(id);

        MembroRequest membroRequest = membroDto.toRequest();


        model.addAttribute("membro", membroRequest);
        model.addAttribute("membroFoto", membroDto.getImagemBase64());
        model.addAttribute("membroId", id);
        model.addAttribute("edit", true);

        return "operational/membros/cadastrar";

    }
    
    
    @PostMapping("/salvar")
    public String salvar(@Valid MembroRequest request, RedirectAttributes redirect) {
        log.info("No controller");

        log.info(request.toString());
        
        BasicResponse response = membroService.salvar(request);

        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());

        
        return "redirect:/membros/";
    }

    @PostMapping("/alterar/{id}")
    public String salvar(@Valid MembroRequest request, @PathVariable Long id, RedirectAttributes redirect) {
        log.info("No controller");

        log.info(request.toString());
        
        BasicResponse response = membroService.editar(request, id);

        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());

        
        return "redirect:/membros/editar/"+id;
    }

    @PostMapping("/excluir/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirect){

        BasicResponse response = this.membroService.deletar(id);

        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/membros/";

    }

    @GetMapping("/frequencia/{id}")
    public String frequencias(@PathVariable Long id, Model model,  RedirectAttributes redirect){

        try {

            MembroDto membroDto = this.membroService.buscarPorId(id);
            AtividadeMembroRequest request = new AtividadeMembroRequest();
            request.setMembroId(id);

            List<AtividadeDto>[] arrayDeListas = this.frequenciaService.dividirAtividadesPorPresençaDeMembro(id);
            model.addAttribute("atividadesVinculadas", arrayDeListas[0]);
            model.addAttribute("atividadesNaoVinculadas", arrayDeListas[1]);
            model.addAttribute("membroId", id);
            model.addAttribute("membro", membroDto);
            model.addAttribute("atividadeMembroRequest", request);


            return  "operational/membros/frequencia";

            
        } catch (NotFoundException e) {
            // TODO: handle exception
            redirect.addFlashAttribute("mensagem", e.getMessage());
            redirect.addFlashAttribute("sucesso", false);

            return "redirect:/membros/";
        }
      
    }

    @PostMapping("/deletarFrequencia/{atividade_id}/{membro_id}")
    @Transactional
    public String deletarFrequencia(@PathVariable Long atividade_id, @PathVariable Long membro_id,
            RedirectAttributes redirectAttributes) {

        BasicResponse response = this.frequenciaService.deletarFrequencia(atividade_id, membro_id);

        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/membros/frequencia/" + membro_id;

    }
    
    @PostMapping("/registrarFrequencia")
    public String registrarFrequencia(@Valid AtividadeMembroRequest request,
            RedirectAttributes redirectAttributes) {

        BasicResponse response = this.frequenciaService.registrarFrequencia(request);

         redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/membros/frequencia/" + request.getMembroId();
        
    }
    
}
