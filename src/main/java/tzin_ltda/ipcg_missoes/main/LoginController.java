package tzin_ltda.ipcg_missoes.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {

     @GetMapping
    public String getLogin(){
        return "login";
    }

    @GetMapping("/error")
    public String loginFail(RedirectAttributes redirect){
        redirect.addFlashAttribute("mensagem", "E-mail ou senha inválidos");
        redirect.addFlashAttribute("sucesso", false);

        return "redirect:/login";
    }


    
}
