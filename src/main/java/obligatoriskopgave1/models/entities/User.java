package obligatoriskopgave1.models.entities;

public class User {

    private int userId;
    private String name;
    private String password;
    private String email;
    private String address;

    public User(int userId, String name, String address, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return userId + " " + name + " " + password + " " + email + " " + address;
    }
}
