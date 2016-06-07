/**
 * Created by Guest on 7/6/16.
 */
package BlogService;

import Entity.Tag;

public class TagServices extends ORManager<Tag>{

    private static TagServices instance;

    private TagServices(){
        super(Tag.class);
    }

    public static TagServices getInstance(){
        if(instance == null)
            instance = new TagServices();

        return instance;
    }

}
