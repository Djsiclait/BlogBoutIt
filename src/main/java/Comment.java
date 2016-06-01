import java.util.Date;

/**
 * Created by Siclait on 30/05/2016.
 */
public class Comment {
    //Attributes
    private long id;
    private String comment;
    private int author;
    private int article;
    private Date pubDate;

    // Constructor
    public Comment(){

    }

    public Comment(long id, String comment, int author, int article, Date pubDate){

        this.id =  id;
        this.comment = comment;
        this.author = author;
        this.article = article;
        this.pubDate = pubDate;
    }

    //Getters
    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public int getAuthor() {
        return author;
    }

    public int getArticle() {
        return article;
    }

    public Date getPubDate() {
        return pubDate;
    }

}
