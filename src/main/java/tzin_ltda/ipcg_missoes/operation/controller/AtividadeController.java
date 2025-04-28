package tzin_ltda.ipcg_missoes.operation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("atividades")
@Log4j2
public class AtividadeController {


    
    @GetMapping("/")
    public String getAll(Model model) {


        return  "operational/atividades/listar";

    }

    @GetMapping("/incluir")
    public String listar(Model model) {


        return  "operational/atividades/cadastrar";

    }
    
}
