import com.sun.org.apache.regexp.internal.RE;
import com.sun.tracing.dtrace.ArgsAttributes;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by Eduardo veras on 01-Jun-16.
 */
public class PageCreator {

    public static DatabaseManager DBmanager;


    public PageCreator() throws Exception {
        DBmanager = new DatabaseManager();

        DBmanager.BootUP();
        DBmanager.PrintData();

        generateGets();
        generatePost();
    }

    private static void generateGets()
    {
        get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            ArrayList listaArticulos = DBmanager.GetAllArticles();
            ArrayList listComments  = DBmanager.GetAllComments();
            attributes.put("comments",listComments);
            if (request.session().attribute("user")!=null)
            {
                attributes.put("user",request.session().attribute("user"));
            }
            else
            {
                attributes.put("user","");
            }
            attributes.put("listaArticulos",listaArticulos);
            attributes.put("message", "Welcome.");
            return new ModelAndView(attributes, "index.ftl");

        }, new FreeMarkerEngine());


        //get("/logout", (req, res) -> req.session(true).invalidate()   );


        get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Welcome.");
            return new ModelAndView(attributes, "login.ftl");

        }, new FreeMarkerEngine());

        get("/register", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Welcome.");
            return new ModelAndView(attributes, "registerPage.ftl");
        }, new FreeMarkerEngine());

        get("/create", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Welcome.");
            return new ModelAndView(attributes, "createPost.ftl");
        }, new FreeMarkerEngine());
    }

    private static void generatePost()
    {
        post("/register", (request, response) -> {

            boolean author=false;
            boolean admin =false;
            String username = request.queryParams("username");
            String name = request.queryParams("name");

            String pass = request.queryParams("password");
            String adminStr = request.queryParams("admin");
            String authorStr = request.queryParams("author");
            if (adminStr!=null)
            {
                admin=true;
            }
            if (authorStr!=null)
            {
                author=true;
            }
            System.out.println("Username:"+username);
            System.out.println("Name:"+name);
            System.out.println("Pass:"+pass);
            System.out.println("admin:"+admin);
            System.out.println("author:"+author);
            DBmanager.CreateUser(username,name,pass,admin,author);
            response.redirect("./");


            //TODO: Add to the database into users
            return "working";
        });


        post("/login", (request, response) -> {

            String username = request.queryParams("username");
            String pass = request.queryParams("password");

            System.out.println("Username:"+username);
            System.out.println("pass:"+pass);
            if (DBmanager.CheckCredentials(username,pass))
            {
                System.out.print("Login Successfull");
                Session session=request.session(true);
                request.session().attribute("user", username) ;
            }
            else
            {
                System.out.print("The user doesnt exist");
            }
            response.redirect("./");

            //TODO: Make the login function with cookies and stuff
            return username;
        });


        post("/", (request, response) -> {
            //TODO:Make this post work

            String comment = request.queryParams("thebodyx");
            String postID = request.queryParams("postID");


            System.out.println("Comment:"+comment);
            System.out.println("Post ID:"+postID);
            DBmanager.CreateComment(comment,"Eduardo",Integer.parseInt(postID));
            response.redirect("./");
            return "lol";
        });




        post("/create", (request, response) -> {
            String title = request.queryParams("title");
            String body = request.queryParams("body");
            String tags = request.queryParams("tags");

            System.out.println("Title:"+title);
            System.out.println("Body:"+body);
            System.out.println("tags:"+tags);
            //ArrayList<String> myList = new ArrayList<String>(Arrays.asList(tags.split("\\s*,\\s*")));
            //System.out.print(myList);
            DBmanager.CreateArticle(title,body,"Eduardo");
            //DBmanager.ProcessTagsOnArticlea(myList,new Article());



            response.redirect("./");
            return "lol";
        });
    }
}
