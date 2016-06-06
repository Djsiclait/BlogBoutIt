import org.jsoup.Jsoup;

import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

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

            ArrayList<Article> listaArticulos = DBmanager.GetAllArticles();
            ArrayList listComments  = DBmanager.GetAllComments();

            attributes.put("comments",listComments);
            ArrayList<tagPair> listaTags= new ArrayList<>();

            for (Article art :listaArticulos) {
                //listaTags
                System.out.println("Something happened------------------------------------------");
                ArrayList<Tag> listaT=DatabaseManager.GetAllArticleTags((int) (long)art.getId());
                System.out.println("Something happened------------------------------------------");
                for (Tag t :listaT) {
                    System.out.println("ENTERED LOOP---------------------------------");
                        listaTags.add(new tagPair((int)art.getId(),t.getTag()));
                }

            }
            for (tagPair t:listaTags) {
                System.out.print("tag"+t.getTag());
            }
            attributes.put("listatags", listaTags);



            if (request.session().attribute("user")!=null)
            {
                User user = DBmanager.FetchUser(request.session().attribute("user"));
                attributes.put("user",user);
            }
            else
            {
                attributes.put("user",DBmanager.FetchUser(""));
            }

            attributes.put("listaArticulos",listaArticulos);
            attributes.put("message", "Welcome");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());


        get("/logout", (req, res) -> {

            req.session().invalidate();
            res.redirect("/");

            return "<h1>You have bee logged out<>";
        }  );


        get("/login", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            attributes.put("message", "Welcome");

            if (request.session().attribute("user")!=null)
            {
                User user = DBmanager.FetchUser(request.session().attribute("user"));
                attributes.put("user",user);
            }
            else
            {
                attributes.put("user",DBmanager.FetchUser(""));
            }

            return new ModelAndView(attributes, "login.ftl");
        }, new FreeMarkerEngine());

        get("/register", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            if (request.session().attribute("user")!=null)
            {
                User user = DBmanager.FetchUser(request.session().attribute("user"));
                attributes.put("user",user);
            }
            else
            {
                attributes.put("user",DBmanager.FetchUser(""));
            }

            attributes.put("message", "Welcome");

            return new ModelAndView(attributes, "registerPage.ftl");
        }, new FreeMarkerEngine());

        get("/create", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            if (request.session().attribute("user")!=null)
            {
                User user = DBmanager.FetchUser(request.session().attribute("user"));
                attributes.put("user",user);
            }
            else
            {
                attributes.put("user",DBmanager.FetchUser(""));
            }

            attributes.put("message", "Welcome");

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

                response.redirect("./");
            }
            else
            {
                System.out.print("The user doesnt exist");

                response.redirect("/login");
            }

            //TODO: Make the login function with cookies and stuff
            return username;
        });

        post("/", (request, response) -> {
            //TODO:Make this post work

            String formType = request.queryParams("kind");

            if (formType.equals("delete"))
            {
                int commentID = Integer.parseInt(request.queryParams("commentID"));
                DBmanager.DeleteComment(commentID);
            }
            else
            {
                String comment = Jsoup.parse(request.queryParams("thebodyx")).text();
                String postID = request.queryParams("postID");
                String user = request.queryParams("user");

                System.out.println("Comment:"+comment);
                System.out.println("Post ID:"+postID);
                System.out.println("User: "+user);

                DBmanager.CreateComment(comment,user,Integer.parseInt(postID));

            }

            response.redirect("./");

            return "lol";
        });




        post("/create", (request, response) -> {
            String title = request.queryParams("title");
            String body = Jsoup.parse(request.queryParams("body")).text();
            String tags = request.queryParams("tags");
            String user = request.queryParams("user");

            System.out.println("Title:"+title);
            System.out.println("Body:"+body);
            System.out.println("tags:"+tags);
            System.out.println("User:"+user);
            System.out.println();

            ArrayList<String> listString = new ArrayList<String>(Arrays.asList(tags.split("\\s*,\\s*")));
            ArrayList<Tag> listTags = new ArrayList<Tag>();

            for (String st:listString) {
                listTags.add(new Tag(st));
            }

            System.out.println("Lista Tags:"+ listTags);
            System.out.println("Salio del for");

            int ID = DBmanager.CreateArticle(title,body,user);
            DBmanager.ProcessTagsOnArticle(listTags,ID);
            //DBmanager.ProcessTagsOnArticlea(listTags,ID);

            response.redirect("./");

            return "lol";
        });
    }
}
