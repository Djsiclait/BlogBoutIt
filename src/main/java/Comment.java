import java.util.Date;

/**
 * Created by Siclait on 30/05/2016.
 */
public class Comment {
    //Attributes
    private long id;
    private String comment;
    private String author;
    private int article;

    // Constructor
    public Comment(int id){
        this.id = id;
    }
    public Comment(int id, int article){
        this.id = id;
        this.article = article;
    }

    public Comment(long id, String comment, String author, int article){

        this.id =  id;
        this.comment = comment;
        this.author = author;
        this.article = article;
    }

    //Getters
    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
    }

    public int getArticle() {
        return article;
    }

}
