/**
 * Created by Siclait on 30/05/2016.
 */
import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Article {
    //Attributes
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String body;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "article")
    private ArrayList<Comment> Comments = new ArrayList<>();

    @OneToMany
    private ArrayList<Tag> Tags = new ArrayList<>();

    //Constuctors
    public Article(){

    }

    public Article(Integer id){
        this.setId(id);
    }

    public Article(Integer id, String title, String body){
        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
    }

    public Article(Integer id, String title, String body, User author){

        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setAuthor(author);
    }

    public Article(Integer id, String title, String body, User autho, ArrayList<Comment> Comments, ArrayList<Tag> Tags){

        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setAuthor(author);
        this.setComments(Comments);
        this.setTags(Tags);
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public ArrayList<Comment> getComments() {
        return Comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        Comments = comments;
    }

    public ArrayList<Tag> getTags() {
        return Tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        Tags = tags;
    }
}
