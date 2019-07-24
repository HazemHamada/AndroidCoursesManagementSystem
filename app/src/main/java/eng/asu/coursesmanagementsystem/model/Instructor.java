package eng.asu.coursesmanagementsystem.model;

import java.io.Serializable;

public class Instructor implements Serializable {
    private int id;
    private String name;
    private String email;
    //private String phone;
    private String bio;
    private String imageUrl;

    public Instructor(int id, String name, String email, String phone, String bio, String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        //this.phone = phone;
        this.bio = bio;
        this.imageUrl = imageUrl;
    }

    public int getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    //public String getPhone() { return phone; }

    public String getBio() { return bio; }

    public String getImageUrl() { return imageUrl; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    //public void setPhone(String phone) { this.phone = phone; }

    public void setBio(String bio) { this.bio = bio; }

    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
