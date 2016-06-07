/**
 * Created by Siclait on 30/05/2016.
 */
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ETIQUETA")
public class Tag implements Serializable{

    private static final long serialVersionUID = 1L;

    // Attributes
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TAG", length = 50, unique = true)
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
