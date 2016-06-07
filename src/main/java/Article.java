/**
 * Created by Siclait on 30/05/2016.
 */
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Entity
@Table(name = "ARTICULO")
public class Article implements Serializable{

    private static final long serialVersionUID = 1L;

    //Attributes
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TITULO", length = 50)
    private String title;

    @Column(name = "CUERPO", length = 80000, unique = true)
    private String body;

    @ManyToOne
    @Column(name = "AUTOR")
    private User author;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    @Column(name = "COMENTARIOS")
    private ArrayList<Comment> Comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "ETIQUETAS")
    private ArrayList<Tag> Tags = new ArrayList<>();

    @GeneratedValue
    @Column(name = "FECHA")
    private Date pubDate;
    @GeneratedValue
    @Column(name = "MODIFICADO")
    private Date modified;

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

    public Article(Integer id, String title, String body, User autho, ArrayList<Comment> Comments, ArrayList<Tag> Tags, Date pubDate, Date modified){

        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setAuthor(author);
        this.setComments(Comments);
        this.setTags(Tags);
        this.setPubDate(pubDate);
        this.setModified(modified);
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

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
