package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.Film;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("mock")
@Component
public class DAOFilmMock implements IDAOFilm {


    @Override
    public List<Film> selectFilms() {

        List<Film> films = new ArrayList<>();
        films.add(new Film(1,"Les Visiteurs", 1993, 105, "En l'an de grace 1123, le comte de Montmirail et son fidèle écuyer, Jacquouille la Fripouille, se retrouvent propulsés en l'an 1992 apres avoir bu une potion magique fabriquée par l'enchanteur Eusaebius, censée leur permettre de se défaire d'un terrible sort."));
        films.add(new Film(2, "Le père Noël est une ordure", 1982, 88,"La permanence téléphonique parisienne SOS détresse-amitié est perturbée le soir de Noël par l'arrivée de personnages marginaux farfelus qui provoquent des catastrophes en chaîne."));

        return films;
    }
}
