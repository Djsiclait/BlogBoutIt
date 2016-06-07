/**
 * Created by Guest on 7/6/16.
 */
package BlogService;

import Entity.Comment;

public class CommentServices extends ORManager<Comment>{

    private static CommentServices instance;

    private CommentServices(){
        super(Comment.class);
    }

    public static CommentServices getInstance(){
        if(instance == null)
            instance = new CommentServices();

        return instance;
    }

}
