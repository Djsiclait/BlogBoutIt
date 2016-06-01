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
    private String author;
    private Date lastEdit;

    //Constuctors
    public Article(){

    }

    public Article(int id){
        this.id = id;
    }

    public Article(int id, String title, String body, Date lastEdit){
        this.id = id;
        this.title = title;
        this.body = body;
        this.lastEdit = lastEdit;
    }

    public Article(long id, String title, String body, String author, Date pubDate, Date lastEdit){

        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.pubDate = pubDate;
        this.lastEdit = lastEdit;
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

    public String getAuthor() {
        return author;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public Date getLastEdit() {
        return lastEdit;
    }
}
