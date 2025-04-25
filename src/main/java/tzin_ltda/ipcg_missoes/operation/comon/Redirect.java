package tzin_ltda.ipcg_missoes.operation.comon;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tzin_ltda.ipcg_missoes.operation.model.response.BasicResponse;

public class Redirect {

    public static void setRedirect(RedirectAttributes redirect, String mensagem, Boolean status){
        
        redirect.addFlashAttribute("mensagem", mensagem);
        redirect.addFlashAttribute("sucesso", status);

    }

    public static void basicRedirect(RedirectAttributes redirect, BasicResponse response){
        
        redirect.addFlashAttribute("mensagem", response.getMessage());
        redirect.addFlashAttribute("sucesso", response.isSucesso());

    }
    
}
