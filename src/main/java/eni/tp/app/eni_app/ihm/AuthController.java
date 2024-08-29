package eni.tp.app.eni_app.ihm;


import eni.tp.app.eni_app.bo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({"loggedUser"})
@Controller
public class AuthController {


    @GetMapping("login")
    public String showLogin(Model model, RedirectAttributes redirectAttributes) {
        //Tester si déjà connecté (le user en session n'est pas null) alors retourner page d'erreur
        User loggedUser = (User) model.getAttribute("loggedUser");

        if (loggedUser != null) {
            //Ajouter un message temporaire (flash message)
            EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_ERROR, "Vous êtes déjà connecté(e)");
            //Plus besoin de la page already-logged-page car on a un flash message maintenant

            return "redirect:/";
        }

        //Instancier un user vide(email et password vide)
        User user = new User("mehdi@rochereau", "mehdi");

        //Envoyer le user dans le Model
        model.addAttribute("user", user);

        return "/auth/login-page";
    }

    @PostMapping("user")
    public String showUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes) {
        //Tu récupère l'user grâce au @ModelAttribute

        //Mettre l'user dans la session
        model.addAttribute("loggedUser", user);
        System.out.println(String.format("L'email %s a été sauvegardé", user.email));

        //Ajouter un message temporaire (flash message)
        //Avant la refactory :
        //redirectAttributes.addFlashAttribute("flashMessage", new EniFlashMessage(EniFlashMessage.TYPE_FLASH_SUCCESS, "Vous êtes connecté(e)"));

        //Après la refactory :
        EniIHMHelpers.sendCommonFlashMessage(redirectAttributes, EniFlashMessage.TYPE_FLASH_SUCCESS, "Vous êtes connecté(e)");

        //rediriger sur ta page d'accueil
        return "redirect:/";
    }

    @GetMapping("logout")
    public String Logout(SessionStatus sessionStatus, RedirectAttributes redirectAttributes) {
        //Nettoyer la session
        sessionStatus.setComplete();

        //Ajouter un message temporaire (flash message)
        EniIHMHelpers.sendSuccessFlashMessage(redirectAttributes, "Vous êtes déconnecté(e)");

        //rediriger à la page d'accueil
        return "redirect:/";

    }

}
