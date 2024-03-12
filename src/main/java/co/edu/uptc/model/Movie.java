package co.edu.uptc.model;

public class Movie extends MultimediaContent {

    private String URLVideo;

    public Movie(int id, String name, String author, String description, int duration) {
        super(id, name, duration, author, description);
        this.URLVideo = "";
    }

    


    @Override
    public String toString() {
        return "Name movie: " + getName() +
                "\nAuthor: " + getAuthor() +
                "\nDuration:  " + getDuration() +
                "\nDescription: " + getDescription();
    }




    public String getURLVideo() {
        return URLVideo;
    }




    public void setURLVideo(String uRLVideo) {
        URLVideo = uRLVideo;
    }

}
