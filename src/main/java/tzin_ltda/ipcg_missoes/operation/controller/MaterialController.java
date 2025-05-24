package tzin_ltda.ipcg_missoes.operation.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tzin_ltda.ipcg_missoes.operation.model.dto.MaterialDto;
import tzin_ltda.ipcg_missoes.operation.model.request.MaterialRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.service.MaterialService;

import java.util.List;

@Controller
@RequestMapping("/materiais")
@RequiredArgsConstructor
@Log4j2
public class MaterialController {

    private final MaterialService materialService;

    @GetMapping("/")
    public String listarMateriais(Model model) {
        List<MaterialDto> materiais = materialService.listarMateriais();
        model.addAttribute("materiais", materiais);
        return "operational/materiais/listar"; 
    }

    @GetMapping("/incluir")
    public String formularioIncluir(Model model) {
        model.addAttribute("material", new MaterialRequest());
        return "operational/materiais/cadastrar"; 
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute MaterialRequest materialRequest, RedirectAttributes redirectAttributes) {
        BasicResponse response = materialService.salvar(materialRequest);
        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());
        return "redirect:/materiais/";
    }

    @GetMapping("/editar/{id}")
public String formularioEditar(@PathVariable Long id, Model model) {
    MaterialDto material = materialService.buscarPorId(id); // Você precisará criar este método no service
    model.addAttribute("material", material);
    model.addAttribute("materialId", id); // Adiciona o ID para o formulário
    return "operational/materiais/editar"; // Retorna a view de edição, não redireciona
}

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable Long id, @ModelAttribute MaterialRequest materialRequest,
                         RedirectAttributes redirectAttributes) {
        BasicResponse response = materialService.editar(materialRequest, id);
        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());
        return "redirect:/materiais/";
    }

    @PostMapping("/excluir/{id}")
    public String deletar(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        BasicResponse response = materialService.deletar(id);
        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());
        return "redirect:/materiais/";
    }

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        MaterialDto dto = materialService.buscarPorId(id);
        model.addAttribute("material", dto);
        model.addAttribute("id", id);
        return "operational/materiais/detalhes";
    }
}
