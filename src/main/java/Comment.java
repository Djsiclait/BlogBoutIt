/**
 * Created by Siclait on 30/05/2016.
 */
public class Comment {
    //Attributes
    private long id;
    private String comment;
    private User author;
    private Article article;

    // Constructor
    public Comment(){

    }

    public Comment(long id, String comment, User author, Article article){

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

    public User getAuthor() {
        return author;
    }

    public Article getArticle() {
        return article;
    }
}
