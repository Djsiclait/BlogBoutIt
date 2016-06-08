/**
 * Created by Siclait on 30/05/2016.
 */
package Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "COMENTARIO")
public class Comment implements Serializable{

    private static final long serialVersionUID = 1L;

    //Attributes
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "COMMENT", length = 500)
    private String comment;

    @ManyToOne // @Column not allowed on @ManyTOne
    private User author;

    @ManyToOne // @Column not allowed on @ManyTOne
    private Article article;

    // Constructor
    public Comment(){

    }

    public Comment(Integer id){
        this.setId(id);
    }

    public Comment(Integer id, Article article){
        this.setId(id);
        this.setArticle(article);
    }

    public Comment(String comment, User author, Article article){

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
