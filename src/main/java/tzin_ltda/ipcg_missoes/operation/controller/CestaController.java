package tzin_ltda.ipcg_missoes.operation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.model.dto.CestaDto;
import tzin_ltda.ipcg_missoes.operation.model.dto.MembroDto;
import tzin_ltda.ipcg_missoes.operation.model.request.CestaRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.service.CestaService;
import tzin_ltda.ipcg_missoes.operation.service.MembroService;

@Controller
@RequestMapping("cestas")
@Log4j2
public class CestaController {

    @Autowired
    private CestaService cestaService;

    @Autowired
    private MembroService membroService;


     @GetMapping("/")
    public String listar(Model model) {

        List<CestaDto> cestaDtos = this.cestaService.listarTodos();


        model.addAttribute("cestas", cestaDtos);


        return  "operational/cestas/listar";

    }

    @GetMapping("/incluir")
    public String incluir(Model model) {

        CestaRequest cestaRequest = new CestaRequest();

        List<MembroDto> membroDtos = this.membroService.listarTodos();

        model.addAttribute("cesta", cestaRequest);
        model.addAttribute("membros", membroDtos);
        model.addAttribute("edit", false);


        return  "operational/cestas/cadastrar";

    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Long id) {

        CestaRequest cestaRequest = this.cestaService.buscarPorId(id).toRequest();

        List<MembroDto> membroDtos = this.membroService.listarTodos();

        model.addAttribute("cesta", cestaRequest);
        model.addAttribute("cestaId", id);
        model.addAttribute("membros", membroDtos);
        model.addAttribute("edit", true);


        return  "operational/cestas/cadastrar";

    }

    
    @PostMapping("/salvar")
    public String salvar(@Valid CestaRequest request, RedirectAttributes redirect) {
        log.info("No controller");

        log.info(request.toString());
        
        BasicResponse response = cestaService.salvar(request);

        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());

        
        return "redirect:/cestas/";
    }

    @PostMapping("/alterar/{id}")
    public String alterar(@Valid CestaRequest request, RedirectAttributes redirect, @PathVariable Long id) {
        log.info("No controller");

        log.info(request.toString());
        
        BasicResponse response = cestaService.editar(request, id);

        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());

        
        return "redirect:/cestas/editar/"+id;
    }

    @PostMapping("/excluir/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirect){

        BasicResponse response = this.cestaService.deletar(id);

        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/cestas/";

    }
    
}
