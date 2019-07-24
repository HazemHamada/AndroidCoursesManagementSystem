package eng.asu.coursesmanagementsystem.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String name;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("desc")
    private String description;
    @SerializedName("min_gpa")
    private float minGPA;
    private Instructor instructor;

    public Course(int id, String name, String imageUrl, String description, float minGPA, Instructor instructor) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.minGPA = minGPA;
        this.instructor = instructor;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getImageUrl() { return imageUrl; }

    public String getDescription() { return description; }

    public float getMinGPA() { return minGPA; }

    public Instructor getInstructor() { return instructor; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public void setDescription(String description) { this.description = description; }

    public void setMinGPA(float minGPA) { this.minGPA = minGPA; }

    public void setInstructor(Instructor instructor) { this.instructor = instructor; }
}
