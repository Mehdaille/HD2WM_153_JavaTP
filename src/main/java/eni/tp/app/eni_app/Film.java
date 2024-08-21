package eni.tp.app.eni_app;

public class Film {

    public long id;
    public String title;
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

}
