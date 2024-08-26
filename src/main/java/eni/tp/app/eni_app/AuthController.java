package eni.tp.app.eni_app;


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
    public String showLogin(Model model) {
        //Tester si déjà connecté (le user en session n'est pas null) alors retourner page d'erreur
        User loggedUser = (User) model.getAttribute("loggedUser");

        if (loggedUser != null) {
            return "auth/already-logged-page";
        }

        //Instancier un user vide(email et password vide)
        User user = new User();

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
        redirectAttributes.addFlashAttribute("flashMessage", "Vous êtes connecté(e) avec succès");

        //rediriger sur ta page d'accueil
        return "redirect:/";
    }

    @GetMapping("logout")
    public String Logout(SessionStatus sessionStatus) {
        //Nettoyer la session
        sessionStatus.setComplete();

        //rediriger à la page d'accueil
        return "redirect:/";

    }
}
