/**
 * Created by Eduardo veras on 06-Jun-16.
 */
public class tagPair {
    private int postID;
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }


    public tagPair(int post, String tag)
    {
        this.tag =tag;
        this.postID=post;
    }
}
