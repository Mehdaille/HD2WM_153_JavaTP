package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.FilmManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Arrays;
import java.util.List;

@Controller
public class FilmController {

    @Autowired
    FilmManager filmManager;

    //"" = ira à la racine de l'url donc => http://localhost:8080
    @GetMapping("")
    public String showAccueil() {

        //Retourne le nom du fichier HTML à charger
        //PS : A partir du dossier template
        //ressources/templates est la racine des fichiers html

        return "accueil";
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
        return "list-films";
    }

    @GetMapping("details-film/{id}")
    public String showFilm(@PathVariable("id") long id, Model model) {
        //Retourne le nom du fichier HTML à charger
        //PS : A partir du dossier template
        //ressources/templates est la racine des fichiers html

        Film film = filmManager.getFilmById(id);

        //Tester si le film n'existe pas
        if (film == null) {
            //Afficher la page d'errur qui s'appelle film-not-found
            return "film-not-found";
        }

        //Envoyer le film trouvé dans la vue (dans le modèle)
        //la partie "film" => c'est ce qu'on utilisera dans l'Html, par exemple : ${film.title}
        model.addAttribute("film", film);

        //Affiche la page détail film
        return "details-film";
    }
}
