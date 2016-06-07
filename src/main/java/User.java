/**
 * Created by Siclait on 30/05/2016.
 */
import javax.persistence.*;

@Entity
public class User {
    // Attributes
    @Id
    private String username;
    private String name;
    private String password;
    private boolean admin;
    private boolean author;

    // Constructors
    public User(String username) {
        this.setUsername(username);
    }

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(String username, String name, String password, boolean admin, boolean author){

        this.setUsername(username);
        this.setName(name);
        this.setPassword(password);
        this.setAdmin(admin);
        this.setAuthor(author);
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAuthor() {
        return author;
    }

    public void setAuthor(boolean author) {
        this.author = author;
    }

}
