/**
 * Created by Siclait on 30/05/2016.
 */
import javax.persistence.*;

@Entity
public class Tag {
    // Attributes
    @Id
    @GeneratedValue
    private long id;
    private String tag;

    //Constructors
    public Tag(){

    }

    public Tag(int id){
        this.setId(id);
    }

    public Tag(String tag){
        this.setTag(tag);
    }

    public Tag(long id, String tag){

        this.setId(id);
        this.setTag(tag);
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
