/**
 * Created by Siclait on 30/05/2016.
 */
public class Tag {
    // Attributes
    private long id;
    private String tag;

    //Constructors
    public Tag(){

    }

    public Tag(long id, String tag){

        this.id = id;
        this.tag = tag;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getTag() {
        return tag;
    }
}
