/**
 * Created by Siclait on 7/6/16.
 */
package BlogService;

import Entity.User;

public class UserServices extends ORManager<User>{

    private static UserServices instance;

    public UserServices(){
        super(User.class);
    }

    public static UserServices getInstance(){
        if(instance  == null)
            instance = new UserServices();

        return instance;
    }

}
