package tzin_ltda.ipcg_missoes.operation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.comon.PercentCalculator;
import tzin_ltda.ipcg_missoes.operation.model.dto.AtividadeDto;
import tzin_ltda.ipcg_missoes.operation.model.dto.MembroDto;
import tzin_ltda.ipcg_missoes.operation.model.dto.VoluntarioDto;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeMembroRequest;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeRequest;
import tzin_ltda.ipcg_missoes.operation.model.request.AtividadeVoluntarioRequest;
import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;
import tzin_ltda.ipcg_missoes.operation.service.AtividadeMembroService;
import tzin_ltda.ipcg_missoes.operation.service.AtividadeService;
import tzin_ltda.ipcg_missoes.operation.service.AtividadeVoluntarioService;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/atividades/")
@Log4j2
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private AtividadeVoluntarioService atividadeVoluntarioService;

    @Autowired
    private AtividadeMembroService atividadeMembroService;

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

    @GetMapping("/detalhes/{id}")
    public String detalhes(@PathVariable Long id, Model model) {
        AtividadeDto dto = atividadeService.buscarPorId(id);
        model.addAttribute("atividade", dto);
        model.addAttribute("id", id);
        return "operational/atividades/detalhes";
    }

    @GetMapping("/voluntarios/{id}")
    public String listarVoluntarios(@PathVariable Long id, Model model, RedirectAttributes redirect) {

        try {
            AtividadeDto dto = atividadeService.buscarPorId(id);

            List<VoluntarioDto>[] arrayDeListas = this.atividadeVoluntarioService.dividirVoluntariosPorPresençaNaAtividade(id);
            List<VoluntarioDto> voluntariosPresentes = arrayDeListas[0];
            List<VoluntarioDto> voluntariosAusentes = arrayDeListas[1];

            Double porcentagemPresentes = PercentCalculator.calcularPresenca(voluntariosPresentes.size(), voluntariosAusentes.size());

            AtividadeVoluntarioRequest request = new AtividadeVoluntarioRequest();
            request.setAtividadeId(id);

            model.addAttribute("atividade", dto);
            model.addAttribute("voluntariosPresentes", voluntariosPresentes);
            model.addAttribute("voluntariosAusentes", voluntariosAusentes);
            model.addAttribute("atividadeVoluntarioRequest", request);
            model.addAttribute("porcentagemPresentes", porcentagemPresentes);



            return "operational/atividades/voluntarios/lista";
            
        } catch (Exception e) {
            // TODO: handle exception
            redirect.addFlashAttribute("mensagem", e.getMessage());
            redirect.addFlashAttribute("sucesso", false);

            return "redirect:/atividades/detalhes/"+id;
        }
       
    }

     @PostMapping("/registrarVoluntario")
    public String registrarFrequencia(@Valid AtividadeVoluntarioRequest request,
            RedirectAttributes redirectAttributes) {

        BasicResponse response = this.atividadeVoluntarioService.registrarFrequencia(request);

        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/atividades/voluntarios/" + request.getAtividadeId();
        
    }

    @PostMapping("/deletarVoluntario/{atividade_id}/{voluntario_id}")
    @Transactional
    public String deletarFrequenciaVoluntario(@PathVariable Long atividade_id, @PathVariable Long voluntario_id,
            RedirectAttributes redirectAttributes) {

        BasicResponse response = this.atividadeVoluntarioService.deletarFrequencia(atividade_id, voluntario_id);

        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/atividades/voluntarios/" + atividade_id;

    }

    
    @GetMapping("/membros/{id}")
    public String listarMembros(@PathVariable Long id, Model model, RedirectAttributes redirect) {

        try {
            AtividadeDto dto = atividadeService.buscarPorId(id);

            List<MembroDto>[] arrayDeListas = this.atividadeMembroService.dividirMembrosPorPresençaNaAtividade(id);
            List<MembroDto> membrosPresentes = arrayDeListas[0];
            List<MembroDto> membrosAusentes = arrayDeListas[1];

            Double porcentagemPresentes = PercentCalculator.calcularPresenca(membrosPresentes.size(), membrosAusentes.size());

            AtividadeMembroRequest request = new AtividadeMembroRequest();
            request.setAtividadeId(id);

            model.addAttribute("atividade", dto);
            model.addAttribute("membrosPresentes", arrayDeListas[0]);
            model.addAttribute("membrosAusentes", arrayDeListas[1]);
            model.addAttribute("atividadeMembroRequest", request);
            model.addAttribute("porcentagemPresentes", porcentagemPresentes);



            return "operational/atividades/membros/lista";
            
        } catch (Exception e) {
            // TODO: handle exception
            redirect.addFlashAttribute("mensagem", e.getMessage());
            redirect.addFlashAttribute("sucesso", false);

            return "redirect:/atividades/detalhes/"+id;
        }
       
    }

    @PostMapping("/registrarMembro")
    public String registrarFrequencia(@Valid AtividadeMembroRequest request,
            RedirectAttributes redirectAttributes) {

        BasicResponse response = this.atividadeMembroService.registrarFrequencia(request);

        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/atividades/membros/" + request.getAtividadeId();
        
    }

    @PostMapping("/deletarMembro/{atividade_id}/{membro_id}")
    @Transactional
    public String deletarFrequenciaMembro(@PathVariable Long atividade_id, @PathVariable Long membro_id,
            RedirectAttributes redirectAttributes) {

        BasicResponse response = this.atividadeMembroService.deletarFrequencia(atividade_id, membro_id);

        redirectAttributes.addFlashAttribute("mensagem", response.getMessage());
        redirectAttributes.addFlashAttribute("sucesso", response.isSucesso());

        return "redirect:/atividades/membros/" + atividade_id;

    }




}
