package eng.asu.coursesmanagementsystem.model;

public class Track {
    private int id;
    private String name;

    public Track(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
