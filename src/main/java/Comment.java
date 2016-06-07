/**
 * Created by Siclait on 30/05/2016.
 */
import javax.persistence.*;

@Entity
public class Comment {
    //Attributes
    @Id
    @GeneratedValue
    private Integer id;
    private String comment;

    @ManyToOne
    private User author;

    @ManyToOne
    private Article article;

    // Constructor
    public Comment(Integer id){
        this.setId(id);
    }

    public Comment(Integer id, Article article){
        this.setId(id);
        this.setArticle(article);
    }

    public Comment(Integer id, String comment, User author, Article article){

        this.setId(id);
        this.setComment(comment);
        this.setAuthor(author);
        this.setArticle(article);
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
