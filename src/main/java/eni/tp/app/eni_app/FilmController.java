package eni.tp.app.eni_app;

import eni.tp.app.eni_app.bll.FilmManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FilmController {

    @Autowired
    FilmManager filmManager;

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

        return "list-films";
    }

    @GetMapping("details-film")
    public String showFilm(Model model) {
        //Retourne le nom du fichier HTML à charger
        //PS : A partir du dossier template
        //ressources/templates est la racine des fichiers html

        return "details-film";
    }
}
