package tzin_ltda.ipcg_missoes.operation.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import tzin_ltda.ipcg_missoes.operation.model.dto.VoluntarioDto;

@Controller
@RequestMapping("membros")
@Log4j2
public class MembroController {

    @GetMapping("/")
    public String getAll(Model model) {


        return  "operational/membros/listar";

    }

    @GetMapping("/incluir")
    public String listar(Model model) {


        return  "operational/membros/cadastrar";

    }
    
}
