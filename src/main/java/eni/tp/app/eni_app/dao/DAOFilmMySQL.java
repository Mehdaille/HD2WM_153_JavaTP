package eni.tp.app.eni_app.dao;

import eni.tp.app.eni_app.bo.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Profile("mysql")
@Component
public class DAOFilmMySQL implements IDAOFilm {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //Le code qui permet de savoir comment convertir/mapper un résultat en SQL en objet Film
    //Comment mapper un résultat SQL en Film
    static final RowMapper<Film> FILM_ROW_MAPPER = new RowMapper<Film>() {
        @Override
        public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
            Film film = new Film();

            film.id = rs.getInt("id");
            film.title = rs.getString("title");
            film.note = rs.getInt("note");
            film.year = rs.getInt("year");
            film.duration = rs.getInt("duration");
            film.synopsis = rs.getString("synopsis");
            film.genre = rs.getString("genre");

            return film;
        }
    };

    @Override
    public List<Film> selectFilms() {
        return jdbcTemplate.query("SELECT * FROM film", FILM_ROW_MAPPER);
    }

    @Override
    public Film selectFilmById(long id) {

        List<Film> films = jdbcTemplate.query("SELECT * FROM film WHERE id = ?", FILM_ROW_MAPPER, id);

        //Si on trouve aucun élément on retourne null
        if (films.size() == 0) {
            return null;
        }

        //Retourner le premier élément
        return films.get(0);
    }

    @Override
    public void save(Film film) {

        //Inserer en base un aliment
        jdbcTemplate.update("INSERT INTO film(id, title, note, year, duration, synopsis, genre) VALUES (?, ?, ?, ?, ?, ?, ?)", film.id, film.title, film.note, film.year, film.duration, film.synopsis, film.genre);
    }
}
