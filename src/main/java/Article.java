/**
 * Created by Siclait on 30/05/2016.
 */

public class Article {
    //Attributes
    private long id;
    private String title;
    private String body;
    private String author;

    //Constuctors
    public Article(){

    }

    public Article(int id){
        this.id = id;
    }

    public Article(int id, String title, String body){
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Article(long id, String title, String body, String author){

        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        if (body.length() > 600)
             return body.substring(0, 600)+" ...";
        else return body;
    }

    public String getFullBody()
    {
        return body;
    }

    public String getAuthor() {
        return author;
    }
}
