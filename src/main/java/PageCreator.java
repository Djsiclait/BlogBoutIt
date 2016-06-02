import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

/**
 * Created by Eduardo veras on 01-Jun-16.
 */
public class PageCreator {

    public PageCreator()
    {
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

            String username = request.queryParams("username");
            String name = request.queryParams("name");
            String pass = request.queryParams("password");
            response.redirect("./");
            System.out.println("Username:"+username);
            //TODO: Add to the database into users
            return name;
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


        post("/home", (request, response) -> {
            //TODO:Make this post work
            String username = request.queryParams("username");

            response.redirect("./");
            System.out.println("Username:"+username);
            return "lol";
        });
    }
}
