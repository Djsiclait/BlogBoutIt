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

    @Column(name = "FECHA")
    private Date pubDate;

    @Column(name = "MODIFICADO")
    private Date modified;

    @Column(name = "LIKES")
    private Integer likes;

    @Column(name = "DISLIKES")
    private Integer dislikes;

    //Constuctors
    public Article(){
        this.setLikes(0);
        this.setDislikes(0);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        this.setPubDate(date);
        this.setModified(date);

    }

    public Article(Integer id) {

        this.setId(id);

        this.setLikes(0);
        this.setDislikes(0);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        this.setPubDate(date);
        this.setModified(date);
    }

    public Article(Integer id, String title, String body){
        this.setId(id);
        this.setTitle(title);
        this.setBody(body);

        this.setLikes(0);
        this.setDislikes(0);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        this.setPubDate(date);
        this.setModified(date);

    }

    public Article(String title, String body, User author){

        this.setTitle(title);
        this.setBody(body);
        this.setAuthor(author);

        this.setLikes(0);
        this.setDislikes(0);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        this.setPubDate(date);
        this.setModified(date);

    }

    public Article(Integer id, String title, String body, User author, Set<Comment> Comments, Set<Tag> Tags){

        this.setId(id);
        this.setTitle(title);
        this.setBody(body);
        this.setAuthor(author);

        this.setComments(Comments);
        this.setTags(Tags);

        this.setLikes(0);
        this.setDislikes(0);

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        this.setPubDate(date);
        this.setModified(date);

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

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }
}
