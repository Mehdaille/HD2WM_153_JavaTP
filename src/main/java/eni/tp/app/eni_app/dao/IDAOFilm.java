package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Film;

import java.util.List;

public interface IDAOFilm {
    List<Film> selectFilms();

    Film selectFilmById(Long id);

   void save (Film film);
}

