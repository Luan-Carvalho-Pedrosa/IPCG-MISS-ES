package tzin_ltda.ipcg_missoes.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

    @GetMapping("/")
    public String mainMenu(Model model) {

        model.addAttribute("mensagem", "IPCG MISSÕES");

        return "index";

    }
    
    
    
}
