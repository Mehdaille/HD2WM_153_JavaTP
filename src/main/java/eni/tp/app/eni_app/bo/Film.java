package eni.tp.app.eni_app.bo;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class Film {

    public Long id;
    @NotBlank(message = "Le titre doit être renseigné")
    public String title;
    //temporaire => note = association d'avis
    public int note = 3;
    @Positive(message = "L''année doit être renseignée")
    @Min(value = 1895, message = "Veuillez saisir une année cohérante")
    public int year;
    @Positive(message = "La durée doit être renseignée")
    @Min(value = 1, message = "La durée doit être supérieur à 1 minutes")
    public int duration;
    @NotBlank(message = "Le synopsis doit être renseigné")
    public String synopsis;
    @NotBlank(message = "Le genre doit être renseigné")
    public String genre;
    @NotBlank(message = "L'image doit être renseignée")
    public String image;

    public Film() {
        super();
    }

    public Film(String title, int year, int duration, String synopsis, String genre, String image) {
        this.title = title;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.genre = genre;
        this.image = image;
    }

    public Film(Long id, String title, int year, int duration, String synopsis, String genre, String image) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
        this.genre = genre;
        this.image = image;
    }

    //    Récupère la note dans une format int
//    @return
    public int getNote() {
        //PLus tard quand on va supprimer le int note
//        La note sera la moyenne des avis

        return note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
