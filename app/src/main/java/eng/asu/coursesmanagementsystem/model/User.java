package eng.asu.coursesmanagementsystem.model;

public class User {
    private int id;
    private String name;
    private String email;
    private String phone;
    private float GPA;

    public User(int id, String name, String email, String phone, float GPA) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.GPA = GPA;
    }

    public int getId() {return id;}

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getPhone() { return phone; }

    public float getGPA() { return GPA; }

    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setPhone(String phone) { this.phone = phone; }

    public void setGPA(float GPA) { this.GPA = GPA; }
}
