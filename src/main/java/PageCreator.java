/**
 * Created by Eduardo veras on 01-Jun-16.
 */

import org.jsoup.Jsoup;

import Entity.*;
import BlogService.DatabaseManager;

import spark.ModelAndView;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.*;

import static spark.Spark.get;
import static spark.Spark.post;

public class PageCreator {

    public PageCreator() throws Exception {

        DatabaseManager.BootUP();
        DatabaseManager.PrintData();

        generateGets();
        generatePost();
    }

    private static void generateGets()
    {
        System.out.println("Generating get methods............................................");
        get("/", (request, response) -> {
            response.redirect("/pages/1");
            return "Hello";
        });

        get("/pages", (request, response) -> {
            response.redirect("/pages/1");
            return "Hello";
        });


        get("/pages/:pagenum", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            int PageNum =  Integer.parseInt(request.params(":pagenum"));
            System.out.print("-------------------------------------------------------------Page Number:"+ PageNum);
            ArrayList<Article> listaArticulosPag = DatabaseManager.GetArticlesOnPage(PageNum);
            List<Article> listaArticulos = DatabaseManager.GetAllArticles();


            for (Article ar:listaArticulosPag) {
                System.out.println("ID: "+ ar.getId());
                System.out.println("Body: "+ ar.getBody());
                System.out.println("Title: "+ ar.getTitle());
                for (Comment com :ar.getComments())
                {
                    System.out.println("     Comment ID:"+com.getId());
                }
            }

            if (request.session().attribute("user")!= null)
            {
                System.out.println("---------------we are in the IF ------------------");

                String CookieUSER= request.session().attribute("user");
                System.out.println("CookieUser: "+CookieUSER);
                User user = DatabaseManager.FetchUser(CookieUSER);
                attributes.put("user", user);
                //System.out.print("|"+CookieUSER+"|");
                //User user = DatabaseManager.FetchUser(CookieUSER);
                //System.out.println("Username: "+user.getUsername());
                //System.out.println("Name: "+user.getName());
                //System.out.println("Password: "+user.getPassword());

                //attributes.put("user", user);
            }
            else
            {
                User user = DatabaseManager.FetchUser("guest");
                System.out.println("---------------we are in the ELSE ------------------");
                System.out.println("Username: "+user.getUsername());
                System.out.println("Name: "+user.getName());
                System.out.println("Password: "+ user.getPassword());

                attributes.put("user", user);
            }

            if (listaArticulosPag==null)
            {
                System.out.print("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                attributes.put("listaArticulos", listaArticulos);
            }else
            {
                attributes.put("listaArticulos", listaArticulosPag);
            }
            //attributes.put("listaArticulos", listaArticulos);
            attributes.put("message", "Welcome");
            attributes.put("pagenum",PageNum);

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());


        get("/logout", (req, res) -> {
            req.session().invalidate();
            res.redirect("/");

            return "<h1>You have bee logged out</h1>";
        }  );


        get("/login", (request, response) -> {
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX PROCESING LOGIN GET METHOD XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("message", "Welcome");
            if (request.session().attribute("user") != null)
            {
                User user = DatabaseManager.FetchUser(request.session().attribute("user"));
                attributes.put("user", user);
            }
            else
            {
                attributes.put("user", DatabaseManager.FetchUser("guest"));
            }
            return new ModelAndView(attributes, "login.ftl");
        }, new FreeMarkerEngine());

        get("/register", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            if (request.session().attribute("user") != null)
            {
                User user = DatabaseManager.FetchUser(request.session().attribute("user"));
                attributes.put("user",user);
            }
            else
            {
                attributes.put("user", DatabaseManager.FetchUser("guest"));
            }

            attributes.put("message", "Welcome");

            return new ModelAndView(attributes, "registerPage.ftl");
        }, new FreeMarkerEngine());

        get("/create", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            if (request.session().attribute("user") != null)
            {
                User user = DatabaseManager.FetchUser(request.session().attribute("user"));
                attributes.put("user",user);
            }
            else
            {
                attributes.put("user", DatabaseManager.FetchUser("guest"));
            }

            attributes.put("message", "Welcome");

            return new ModelAndView(attributes, "createPost.ftl");
        }, new FreeMarkerEngine());
    }

    private static void generatePost()
    {
        System.out.println("Generating POST methods......................");
        post("/register", (request, response) -> {

            boolean author = false;
            boolean admin = false;

            String username = request.queryParams("username");
            String name = request.queryParams("name");
            String pass = request.queryParams("password");
            String adminStr = request.queryParams("admin");
            String authorStr = request.queryParams("author");

            if (adminStr != null)
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

            DatabaseManager.CreateUser(username,name,pass,admin,author);

            Session session=request.session(true);

            request.session().attribute("user", username) ;

            response.redirect("/");

            return "working";
        });

        post("/login", (request, response) -> {

            String username = request.queryParams("username");
            String pass = request.queryParams("password");

            System.out.println("Username:"+username);
            System.out.println("pass:"+pass);

            if (DatabaseManager.CheckCredentials(username,pass))
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

        post("/pages/:pagenum", (request, response) -> {
            //TODO:Make this post work
            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXX  PROCESING HOMEPAGE POST XXXXXXXXXXXXXXXXXXXXX");
            String formType = request.queryParams("kind");

            if (formType.equals("delete"))
            {
                int commentID = Integer.parseInt(request.queryParams("commentID"));
                DatabaseManager.DeleteComment(commentID);
            }
            else if (formType.equals("like"))
            {
                System.out.println("im liking something");
                DatabaseManager.LikeArticle(Integer.parseInt(request.queryParams("postID")));
            }
            else if (formType.equals("dislike"))
            {
                System.out.println("im disliking something");
                DatabaseManager.DislikeArticle(Integer.parseInt(request.queryParams("postID")));
            }
            else
            {
                String comment = Jsoup.parse(request.queryParams("thebodyx")).text();
                Article article = DatabaseManager.FetchArticle(Integer.parseInt(request.queryParams("postID")));
                User user = DatabaseManager.FetchUser(request.queryParams("user"));
                DatabaseManager.CreateComment(comment,user,article);
            }

            response.redirect("/");

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

            ArrayList<String> listString = new ArrayList<>(Arrays.asList(tags.split("\\s*,\\s*")));
            ArrayList<Tag> listTags = new ArrayList<>();

            for (String st:listString) {
                listTags.add(new Tag(st));
            }

            System.out.println("Lista Tags:"+ listTags);
            System.out.println("Salio del for");

            Integer ID = DatabaseManager.CreateArticle(title,body, DatabaseManager.FetchUser(user));
            DatabaseManager.ProcessTagsOnArticle(listTags, DatabaseManager.FetchArticle(ID));

            response.redirect("/");

            return "lol";
        });
    }
}
