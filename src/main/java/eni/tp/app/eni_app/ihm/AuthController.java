package eni.tp.app.eni_app.ihm;


import eni.tp.app.eni_app.bll.AuthManager;
import eni.tp.app.eni_app.bll.EniManagerResponse;
import eni.tp.app.eni_app.bo.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@SessionAttributes({"loggedUser"})
@Controller
public class AuthController {

    @Autowired
    AuthManager authManager;

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
    public String showUser(@Valid @ModelAttribute(name="user") User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        //Tu récupère l'user grâce au @ModelAttribute

        //1 : Contrôle de surface

        //Erreur : si controle de surface
        if (bindingResult.hasErrors()) {
            return "/auth/login-page";
        }

        //2 : Contrôle métier (le manager)
        EniManagerResponse<User> response = authManager.authenticate(user.email, user.password);


        //Erreur code 756 retourner la page avec l'erreur métier
        if(response.code.equals("756")){
            //TODO : Pendant qu'on retourne la page de connexion (envoyer l'erreur métier)
            return "auth/login-page";
        }

        //3 : Connecter l'user en session
        //Mettre l'user retrouvé en base et le mettre dans la session
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
