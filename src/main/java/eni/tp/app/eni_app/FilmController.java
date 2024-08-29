package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.FilmManager;

import eni.tp.app.eni_app.bo.Film;
import eni.tp.app.eni_app.bo.User;
import eni.tp.app.eni_app.ihm.EniFlashMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;


//Le controller hérite d'un profil utilisateur
@SessionAttributes({"loggedUser"})
@Controller
public class FilmController {

    @Autowired
    FilmManager filmManager;

    @Autowired
    LocaleResolver localeResolver;

    @GetMapping("change-lang/{lang}")
    public String changeLang(@PathVariable("lang") String lang, HttpServletRequest request, HttpServletResponse response) {
        //Instancier la clé de la lang
        Locale locale = Locale.forLanguageTag(lang);

        //Appliquer la langue (en Anglais)
        localeResolver.setLocale(request, response, locale);

        //Rediriger
        return "redirect:/";
    }

    //"" = ira à la racine de l'url donc => http://localhost:8080
    @GetMapping("")
    public String showAccueil() {

        //Retourne le nom du fichier HTML à charger
        //PS : A partir du dossier template
        //ressources/templates est la racine des fichiers html

        return "/v3/accueil-v3";
    }

    @GetMapping("list-films")
    public String showListFilm(Model model) {
        //Envoyer la liste d'aliments dans le Modèle
        model.addAttribute("films", filmManager.getFilms());

        //Retourne le nom du fichier HTML à charger
        //PS : A partir du dossier template
        //ressources/templates est la racine des fichiers html

        // Envoyer la note maximale
        List<Integer> maxStars = Arrays.asList(1, 2, 3, 4, 5);
        model.addAttribute("maxStars", maxStars);
        return "/v3/list-films-v3";
    }

    @GetMapping("details-film/{id}")
    public String showFilm(@PathVariable("id") Long id, Model model) {
        //Retourne le nom du fichier HTML à charger
        //PS : A partir du dossier template
        //ressources/templates est la racine des fichiers html

        Film film = filmManager.getFilmById(id);

        //Tester si le film n'existe pas
        if (film == null) {
            //Afficher la page d'errur qui s'appelle film-not-found
            return "/v3/film-not-found-v3";
        }

        //Envoyer le film trouvé dans la vue (dans le modèle)
        //la partie "film" => c'est ce qu'on utilisera dans l'Html, par exemple : ${film.title}
        model.addAttribute("film", film);

        //Affiche la page détail film
        return "/v3/details-film-v3";
    }

    @GetMapping("show-user-page")
    public String showUserPage(User connectedUser) {

        return "/auth/user-page";
    }

    /**
     * Soi je vais dans la page formulaire avec un id (donc edition)
     * Ou soi je vais dans la page formulaire sans id (donc creation)
     * @param model
     * @return
     */
    @GetMapping({"show-film-formulaire/{id}", "show-film-formulaire"})
    public String showAddFilm(@PathVariable(required=false) Long id, Model model) {
        //Instancier un film apr défaut
        //Film film = new Film();
        Film film = new Film("Astérix et Obélix : Mission Cléopâtre", 2023, 112, "Cléopâtre, la reine d’Égypte, décide, pour défier l'Empereur romain Jules César, de construire en trois mois un palais somptueux en plein désert. Si elle y parvient, celui-ci devra concéder publiquement que le peuple égyptien est le plus grand de tous les peuples. Pour ce faire, Cléopâtre fait appel à Numérobis, un architecte d'avant-garde plein d'énergie. S'il réussit, elle le couvrira d'or. S'il échoue, elle le jettera aux crocodiles. Celui-ci, conscient du défi à relever, cherche de l'aide auprès de son vieil ami Panoramix. Le druide fait le voyage en Égypte avec Astérix et Obélix. De son côté, Amonbofis, l'architecte officiel de Cléopâtre, jaloux que la reine ait choisi Numérobis pour construire le palais, va tout mettre en œuvre pour faire échouer son concurrent.", "Comédie", "https://fr.web.img3.acsta.net/c_310_420/pictures/23/06/21/12/06/4953335.jpg");

        //S'il y un id, le film on le récupère grace à l'id
        //PS : On écrase le iflm vide qu'on voulait afficher dans le form
        //Donc on affichera un film existant dans le formulaire
        if (id != null) {
            film = filmManager.getFilmById(id);
        }

        //Envoyer le film dans le model
        model.addAttribute("film", film);

        //Afficher le formualire
        return "/form/film-formulaire-v3";
    }



    @PostMapping( "show-film-formulaire")
    public String showFilmAdded(@Valid @ModelAttribute Film film, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        //Tu récupère le film grâce au @ModelAttribute

        if (bindingResult.hasErrors()) {
            System.out.println("Erreur de contrôle surface");
        }

        //On sauvegarde le film en BDD
        filmManager.saveFilm(film);

        //Ajouter un message temporaire (flash message)
        redirectAttributes.addFlashAttribute("flashMessage", new EniFlashMessage(EniFlashMessage.TYPE_FLASH_SUCCESS, "Le film a été ajouté à la liste de film"));

        //rediriger sur ta page d'accueil
        return "redirect:/";
    }
}
