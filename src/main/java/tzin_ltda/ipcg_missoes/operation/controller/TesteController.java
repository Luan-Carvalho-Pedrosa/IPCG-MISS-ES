package tzin_ltda.ipcg_missoes.operation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class TesteController {

    @GetMapping("/teste")
    public String teste(Model model) {
        ((Model) model).addAttribute("mensagem", "Funcionando!");
        return "teste";
    }
}
