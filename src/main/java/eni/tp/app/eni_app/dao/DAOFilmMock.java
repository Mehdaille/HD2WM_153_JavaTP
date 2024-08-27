package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Film;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Profile("mock")
@Component
public class DAOFilmMock implements IDAOFilm {

    //Initialiser une faux films
    List<Film> films = Arrays.asList(
            new Film(1, "Les Visiteurs", 1993, 105, "En l'an de grace 1123, le comte de Montmirail et son fidèle écuyer, Jacquouille la Fripouille, se retrouvent propulsés en l'an 1992 apres avoir bu une potion magique fabriquée par l'enchanteur Eusaebius, censée leur permettre de se défaire d'un terrible sort.", "Comédie"),
            new Film(2, "Le père Noël est une ordure", 1982, 88, "La permanence téléphonique parisienne SOS détresse-amitié est perturbée le soir de Noël par l'arrivée de personnages marginaux farfelus qui provoquent des catastrophes en chaîne.", "Comédie"),
            new Film(3, "Un air de famille", 1996, 110, "Toutes les semaines dans la famille Menard, on se réunit au café dont Henri est le patron et on va manger tous ensemble Aux ducs de Bretagne. Ce soir, qui est pourtant un jour de fête, car c'est l'anniversaire de Yolande la belle-fille, un incident va venir troubler les habitudes. Arlette, la femme d'Henri, est partie une semaine pour réfléchir, ce qui va déstabiliser les autres membres de la famille.", "Comédie"));


    @Override
    public List<Film> selectFilms() {
        return films;
    }

    @Override
    public Film selectFilmById(long id) {

        //Predicate qui va filtrer les aliments en fonction de l'id et garder le 1er aliment qu'il trouve
        // Filtrer avec un Predicate
        // .stream => possibilité d'etendre la manipulation de la liste
        // .filter => retourne les elements filtrés (qui respecte la condition)
        // .findFirst => car je veux forcer à avoir/retourner qu'un seul element
        // .get() => comme c'est nullable (Optionnal) je pars du principe qu'il n'est pas null pour l'instant
        Film filmToFound = films.stream().filter(film -> film.id == id).findFirst().orElse(null);

        return filmToFound;
    }
}
