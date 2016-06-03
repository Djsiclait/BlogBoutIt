import com.sun.org.apache.regexp.internal.RE;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            attributes.put("message", "Welcome.");
            return new ModelAndView(attributes, "index.ftl");

        }, new FreeMarkerEngine());


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
            response.redirect("./");
            System.out.println("Username:"+username);
            System.out.println("pass:"+pass);

            //TODO: Make the login function with cookies and stuff
            return username;
        });


        post("/", (request, response) -> {
            //TODO:Make this post work
            String comment = request.queryParams("comment");
            String postID = request.queryParams("postID");

            response.redirect("./");
            System.out.println("Comment:"+comment);
            System.out.println("Post ID:"+postID);
            return "lol";
        });


        post("/create", (request, response) -> {
            String title = request.queryParams("title");
            String body = request.queryParams("body");
            String tags = request.queryParams("tags");



            System.out.println("Title:"+title);
            System.out.println("Body:"+body);
            System.out.println("tags:"+tags);
            //DBmanager.CreateArticle("1",title,body);
            response.redirect("./");
            return "lol";
        });
    }
}
