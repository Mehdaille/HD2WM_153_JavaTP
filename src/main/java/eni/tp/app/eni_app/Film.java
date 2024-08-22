package eni.tp.app.eni_app;

public class Film {

    public long id;
    public String title;
    //temporaire => note = association d'avis
    public int note = 3;
    public int year;
    public int duration;
    public String synopsis;

    public Film (long id, String title, int year, int duration, String synopsis) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.duration = duration;
        this.synopsis = synopsis;
    }

//    Récupère la note dans une format int
//    @return
    public int getNote() {
    //PLus tard quand on va supprimer le int note
//        La note sera la moyenne des avis

        return note;
    }

}
