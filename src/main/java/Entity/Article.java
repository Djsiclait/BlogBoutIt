/**
 * Created by Siclait on 30/05/2016.
 */
package Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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

    @ManyToOne  // @Column not allowed on @ManyTOne
    private User author;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    @Column(name = "COMENTARIOS")
    private Set<Comment> Comments; // Must use Set

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "ETIQUETAS")
    private Set<Tag> Tags; // Must use Set

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

    public Article(String title, String body, User author){

        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setAuthor(author);
    }

    public Article(Integer id, String title, String body, User autho, Set<Comment> Comments, Set<Tag> Tags){

        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setAuthor(author);
        this.setComments(Comments);
        this.setTags(Tags);
    }

    public Article(Integer id, String title, String body, User autho, Set<Comment> Comments, Set<Tag> Tags, Date pubDate, Date modified){

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

    public Set<Comment> getComments() {
        return Comments;
    }

    public void setComments(Set<Comment> comments) {
        Comments = comments;
    }

    public Set<Tag> getTags() {
        return Tags;
    }

    public void setTags(Set<Tag> tags) {
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
