package tzin_ltda.ipcg_missoes.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeRequest;
import tzin_ltda.ipcg_missoes.operation.model.request.VoluntarioRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.service.AtividadeService;

import java.util.List;

@Controller
@RequestMapping("/atividades/")
@Log4j2
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @GetMapping({"", "/"})
    public String getAll(Model model) {
        List<AtividadeDto> atividades = atividadeService.listarAtividades();
        model.addAttribute("atividades", atividades);
        return "operational/atividades/listar";
    }

    @GetMapping("/incluir")
    public String cadastrarForm(Model model) {
        model.addAttribute("atividadeRequest", new AtividadeRequest());
        model.addAttribute("edit", false);
        return "operational/atividades/cadastrar";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute AtividadeRequest atividadeRequest, Model model) {
        BasicResponse response = atividadeService.salvar(atividadeRequest);
        model.addAttribute("mensagem", response.getMessage());
        return "redirect:/atividades/";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        AtividadeDto dto = atividadeService.buscarPorId(id);
        model.addAttribute("atividadeRequest", dto.toRequest());
        model.addAttribute("id", id);
        return "operational/atividades/editar";
    }

    @PostMapping("/editar/{id}")
    public String editar(@ModelAttribute AtividadeRequest atividadeRequest, @PathVariable Long id, Model model) {
        BasicResponse response = atividadeService.editar(atividadeRequest, id);
        model.addAttribute("mensagem", response.getMessage());
        model.addAttribute("edit", true);

        return "redirect:/atividades/";
    }

@PostMapping("/excluir/{id}")
    public String deletar(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        BasicResponse response = atividadeService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());
        return "redirect:/atividades/";
    }
}
