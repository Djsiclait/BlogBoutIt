/**
 * Created by Siclait on 30/05/2016.
 */
import java.util.ArrayList;
import java.util.Date;

public class Article {
    //Attributes
    private long id;
    private String title;
    private String body;
    private Date pubDate;
    private User author;
    private ArrayList<Comment> comments;
    private ArrayList<Tag> tags;

    //Constuctors
    public Article(){

    }

    public Article(long id, String title, String body, User author, Date pubDate, ArrayList<Comment> comments, ArrayList<Tag> tags){

        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.pubDate = pubDate;
        this.comments = comments;
        this.tags = tags;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }
}
