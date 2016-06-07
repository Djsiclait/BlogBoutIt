/**
 * Created by Siclait on 30/05/2016.
 */
import javax.persistence.*;

@Entity
public class Tag {
    // Attributes
    @Id
    @GeneratedValue
    private Integer id;
    private String tag;

    //Constructors
    public Tag(){

    }

    public Tag(Integer id){
        this.setId(id);
    }

    public Tag(String tag){
        this.setTag(tag);
    }

    public Tag(Integer id, String tag){

        this.setId(id);
        this.setTag(tag);
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
