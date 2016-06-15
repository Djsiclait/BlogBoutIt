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
        Chat chat = new Chat();

        System.out.println("Generating get methods............................................");
        get("/", (request, response) -> {
            //response.redirect("/pages/1");
            //chat.broadcastAdminMessage("incognito", request.queryParams("message"));
            Map<String, Object> attributes = new HashMap<>();

            //return "Hello";
        //});
            return new ModelAndView(attributes, "test.ftl");
        }, new FreeMarkerEngine());

        get("/pages", (request, response) -> {
            response.redirect("/pages/1");
            return "Hello";
        });


        get("/pages/:pagenum", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            int PageNum =  Integer.parseInt(request.params(":pagenum"));
            ArrayList<Article> listaArticulosPag = DatabaseManager.GetArticlesOnPage(PageNum);
            List<Article> listaArticulos = DatabaseManager.GetAllArticles();

            if (request.session().attribute("user")!= null)
            {
                String CookieUSER= request.session().attribute("user");

                User user = DatabaseManager.FetchUser(CookieUSER);
                attributes.put("user", user);
            }
            else
            {
                User user = DatabaseManager.FetchUser("guest");

                attributes.put("user", user);
            }

            if (listaArticulosPag == null)
            {
                attributes.put("listaArticulos", listaArticulos);
            }else
            {
                attributes.put("listaArticulos", listaArticulosPag);
            }

            attributes.put("message", "Welcome");
            attributes.put("pagenum",PageNum);
            attributes.put("pages", DatabaseManager.totalPage);

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());


        get("/logout", (req, res) -> {
            req.session().invalidate();
            res.redirect("/");

            return "<h1>You have bee logged out</h1>";
        }  );


        get("/login", (request, response) -> {
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

        get("/:username/chatboard", (request, response) -> {

            Map<String, Object> attributes = new HashMap<>();

            attributes.put("message", "Welcome");

            return new ModelAndView(attributes, "chatboard.ftl");
        }, new FreeMarkerEngine());


    }

    private static void generatePost()
    {

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

            DatabaseManager.CreateUser(username,name,pass,admin,author);

            Session session=request.session(true);

            request.session().attribute("user", username) ;

            response.redirect("/");

            return "working";
        });

        post("/login", (request, response) -> {

            String username = request.queryParams("username");
            String pass = request.queryParams("password");

            if (DatabaseManager.CheckCredentials(username,pass))
            {
                Session session=request.session(true);

                request.session().attribute("user", username) ;

                response.redirect("./");
            }
            else
            {
                response.redirect("/login");
            }

            return username;
        });

        post("/pages/:pagenum", (request, response) -> {
            String formType = request.queryParams("kind");

            if (formType.equals("delete"))
            {
                int commentID = Integer.parseInt(request.queryParams("commentID"));
                DatabaseManager.DeleteComment(commentID);
            }
            else if (formType.equals("like"))
            {
                DatabaseManager.LikeArticle(Integer.parseInt(request.queryParams("postID")));
            }
            else if (formType.equals("deleteP"))
            {
                DatabaseManager.DeleteArticle(Integer.parseInt(request.queryParams("postID")));
            }
            else if (formType.equals("edit"))
            {
                //DatabaseManager.EditArticle(Integer.parseInt(request.queryParams("postID")));
            }
            else if (formType.equals("dislike"))
            {
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

            ArrayList<String> listString = new ArrayList<>(Arrays.asList(tags.split("\\s*,\\s*")));
            ArrayList<Tag> listTags = new ArrayList<>();

            for (String st:listString) {
                listTags.add(new Tag(st));
            }

            Integer ID = DatabaseManager.CreateArticle(title,body, DatabaseManager.FetchUser(user));
            DatabaseManager.ProcessTagsOnArticle(listTags, ID); //DatabaseManager.FetchArticle(ID));

            response.redirect("/");

            return "lol";
        });
    }
}
