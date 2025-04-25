package tzin_ltda.ipcg_missoes.operation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.model.dto.VoluntarioDto;
import tzin_ltda.ipcg_missoes.operation.model.request.VoluntarioRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.service.VoluntarioService;

@Controller
@RequestMapping("voluntarios")
@Log4j2
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    @GetMapping("/")
    public String getAll(Model model) {
        List<VoluntarioDto> voluntarios =  voluntarioService.listarVoluntarios();

        model.addAttribute("voluntarios", voluntarios);

        return  "operational/voluntarios/listar";

    }

    @GetMapping("/incluir")
    public String incluir(Model model){

        model.addAttribute("voluntario", new VoluntarioRequest());
        model.addAttribute("edit", false);
        return "operational/voluntarios/cadastrar";

    }

    @GetMapping("/editar/{id}")
    public String editar(Model model, @PathVariable Long id){

        VoluntarioDto dto = this.voluntarioService.buscarVoluntarioPorId(id);

        model.addAttribute("voluntario", dto.toRequest());
        model.addAttribute("voluntarioId", id);
        model.addAttribute("edit", true);
        return "operational/voluntarios/cadastrar";

    }

    @PostMapping("/salvar")
    public String salvar(@Valid VoluntarioRequest request, RedirectAttributes redirect) {
        try {
            log.info("No controller!");

            log.info(request.toString());
        
            BasicResponse response = voluntarioService.salvar(request);
    
            redirect.addFlashAttribute("mensagem", response.getMessage());
            redirect.addFlashAttribute("sucesso", response.isSucesso());
    
            return "redirect:/voluntarios/";
            
        } catch (Exception e) {
            // TODO: handle exception

            return "operational/voluntarios/cadastrar";
        }
       

    } 

    @PostMapping("/alterar/{id}")
    public String alterar(@Valid VoluntarioRequest request,  @PathVariable Long id, RedirectAttributes redirect) {
        BasicResponse response = voluntarioService.editar(request, id);

        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/voluntarios/editar/"+id;

    } 

    @PostMapping("/excluir/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirect){

        BasicResponse response = voluntarioService.deletarVoluntario(id);

        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());



        return "redirect:/voluntarios/";

    }
    
}
