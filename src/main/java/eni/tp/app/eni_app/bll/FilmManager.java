package eni.tp.app.eni_app.bll;


import eni.tp.app.eni_app.Film;
import eni.tp.app.eni_app.dao.IDAOFilm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmManager {

    @Autowired
    IDAOFilm daofilm;

    public List<Film> getFilms () {
        List<Film> films = daofilm.selectFilms();

        return films;
    }

    public Film getFilmById(long id) {
        //Récupère un film de la DAO
        Film film = daofilm.selectFilmById(id);

        return film;
    }

}
