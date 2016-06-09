/**
 * Created by Siclait on 30/05/2016.
 */
package BlogService;

import Entity.*;

import Entity.Comment;
import Entity.User;
import org.h2.engine.*;
import org.h2.tools.Server;

import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DatabaseManager {

    // Attributes
    private static Connection conn;
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:tcp://localhost:9092/~/Blog;IFEXISTS=TRUE;";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private static ArrayList<Object> archives;


    private DatabaseManager(){

    }
    /*
    public DatabaseManager(){

        try {
            Server server = Server.createTcpServer("-tcpAllowOthers").start();
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("Connection Successful!");
        }
        catch(ClassNotFoundException exp)
        {
            System.out.println("Class ERROR --> " + exp.getMessage());
        }
        catch(SQLException exp)
        {
            System.out.println("Sql ERROR --> " + exp.getMessage());
        }
        catch(Exception exp)
        {
            System.out.println("General ERROR --> " + exp.getMessage());
        }
    } */

    public static void BootUP(){

        List<User> Users = UserServices.getInstance().FindAll();

        if(Users.size() == 0) {
            System.out.println("Creating Admin ...");

            UserServices.getInstance().Create(new User("admin", "Administrador", "admin", true, true));
            UserServices.getInstance().Create(new User("Djsiclait", "Djidjelly Siclait", "1234", true, true));
            UserServices.getInstance().Create(new User("Wardo", "Eduardo Veras", "1234", true, true));

            System.out.println("Admin Created Successfully!");
        }
        else
            System.out.println("Database already configured");

        //DeleteComment(7);

        CreateTag("#Yolo");
        CreateTag("#Swag");
        CreateTag("flo");
        CreateTag("MaxFlo");
        CreateTag("#DeeperThanDeep");

        /*try {
            Statement stat = conn.createStatement();

            System.out.println(stat.toString());
            ResultSet rs = stat.executeQuery("SELECT * FROM USUARIO where username = 'admin'");

            if (!rs.next()) {
                stat.executeUpdate("INSERT INTO USUARIO Values ('admin', 'Administrator', 'admin', 1, 1)");
                System.out.println("Admin created");
            }

            rs = stat.executeQuery("SELECT * FROM USUARIO where username = ''");

            if (!rs.next()) {
                stat.executeUpdate("INSERT INTO USUARIO Values ('', '', 'yolo', 0, 0)");
                System.out.println("Null user created");
            }
        } catch (SQLDataException exp) {
            System.out.println("Data ERROR! --> " + exp.getMessage());
        } catch (SQLException exp) {
            System.out.println("SQL ERROR! --> " + exp.getMessage());
        } catch (Exception exp) {
            System.out.println("General ERROR! --> " + exp.getMessage());
        }*/
    }

    /*public static Connection getConn(){
        return conn;
    }*/

    public static void PrintData(){

        List<User> Users = UserServices.getInstance().FindAll();

        for (User user:
             Users) {
            System.out.println("Username:" + user.getUsername() + " Password:" + user.getPassword());
        }
        /*
        try {
            Statement stat = conn.createStatement();

            System.out.println(stat.toString());
            ResultSet rs = stat.executeQuery("SELECT * FROM USUARIO");
            while (rs.next())
            {
                System.out.println("Username:"+rs.getString("USERNAME")+ " Password:" + rs.getString("PASSWORD"));
            }

        } catch (SQLDataException exp) {
            System.out.println("Data ERROR! --> " + exp.getMessage());
        } catch (SQLException exp) {
            System.out.println("SQL ERROR! --> " + exp.getMessage());
        } catch (Exception exp) {
            System.out.println("General ERROR! --> " + exp.getMessage());
        }
        */

    }

    /*public static void CloseServerConnection(){

        try {
            conn.close();
        }
        catch(SQLException exp)
        {
            System.out.println("Sql ERROR --> " + exp.getMessage());
        }
        catch(Exception exp)
        {
            System.out.println("General ERROR --> " + exp.getMessage());
        }
    }*/


    // Basic Query Functions
    /*
     * Article Entity Query
     */
    public static Integer CreateArticle(String title, String body, User author){

        Article article = new Article(title, body, author);

        ArticleServices.getInstance().Create(article);

        // Using body as a pseudo-key given it is a unique attribute
        List<Article> articles = GetAllArticles();

        for (Article art:
             articles) {
            if(art.getBody().equals(body))
                article = art;
        }

        return article.getId();
    }

    public static void DeleteArticle(int id){

        Article article = ArticleServices.getInstance().Find(id);

        ArticleServices.getInstance().Delete(article);
    }

    public static void EditArticle(int id, String title, String body){

        Article article = ArticleServices.getInstance().Find(id);

        article.setTitle(title);
        article.setBody(body);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        article.setModified(date);

        ArticleServices.getInstance().Edit(article);
    }

    // TODO: Modify this function to be able to search by Tag
    /*public static ArrayList<Article> SearchArchivesBy(Article article, String category){

        return (ArrayList<Article>) ArticleQuery(article, category);
    }*/

    public static List<Article> GetAllArticles(){

        return ArticleServices.getInstance().FindAll();
    }

    /*
     * User Entity Queries
     */
    public static void CreateUser(String username, String name, String password, boolean admin, boolean author){

        User user = new User(username, name, password, admin, author);

        UserServices.getInstance().Create(user);
    }

    public static boolean isUsernameTaken(String username) {

        User user = UserServices.getInstance().Find(username);

        if(user == null) // User does not exist
            return false;
        else
            return true;
    }

    public static void DeleteUser(String username){

        User user = UserServices.getInstance().Find(username);

        UserServices.getInstance().Delete(user);
    }

    // TODO: Discuss if function is really necessary
    public static void MakeAdmin(String username){

        User user = UserServices.getInstance().Find(username);

        if(!user.isAdmin()) {
            System.out.println("Making new admin ...");
            user.setAdmin(true);
            UserServices.getInstance().Edit(user);
            System.out.println("New admin added!");
        }
        else
            System.out.println("This user is already an admin");

    }

    public static void EditUser(String username, String newName, String newPassword){

        boolean different = false;

        User user = UserServices.getInstance().Find(username);

        if(!user.getName().equals(newName)) {
            user.setName(newName);
            different = true;
        }

        if(!user.getPassword().equals(newPassword)) {
            user.setPassword(newPassword);
            different = true;
        }

        if(different) {
            UserServices.getInstance().Edit(user);System.out.println("This user is already an admin");
            System.out.println("User edited");
        }
        else
            System.out.println("No change is needed");

    }

    public static boolean isAdmin(String username){

        User user = UserServices.getInstance().Find(username);

        return user.isAdmin();
    }

    public static boolean CheckCredentials(String username, String password){

        User user = UserServices.getInstance().Find(username);

        if(user == null) // user doesn't exist
            return false;
        else if (!user.getPassword().equals(password)) // wrong password
            return false;
        else
            return true;

    }

    public static User FetchUser(String username){

        return UserServices.getInstance().Find(username);

    }

    /*
     * Comment Entity Query
    */
    public static void CreateComment(String comment, User author, Article article){

        Comment com = new Comment(comment, author, article);

        CommentServices.getInstance().Create(com);
    }

    public static void DeleteComment(int id){

        Comment comment = CommentServices.getInstance().Find(id);

        CommentServices.getInstance().Delete(comment);
    }

    public static ArrayList<Comment> GetArticleComments(Article article){

        ArrayList<Comment> list = new ArrayList<>();

        List<Comment> comments = GetAllComments();

        for (Comment comment:
             comments) {
            if(comment.getArticle().getId() == article.getId())
              list.add(comment);
        }

        return list;
    }

    public static List<Comment> GetAllComments(){

        return CommentServices.getInstance().FindAll();
    }

    /*
    // Tag Query
    private static Object TagQuery(Tag tag, String query){

        try
        {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            switch (query)
            {
                case "insert":

                    stat.execute("INSERT INTO ETIQUETA (TAG) VALUES ('" +
                            tag.getTag() + "')");

                    return null;

                case "isNew": // search query

                    rs = stat.executeQuery("Select * From ETIQUETA WHERE TAG='" +
                            tag.getTag() + "'");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new Tag(rs.getInt("id"),
                                rs.getString("tag")));

                    if(archives.size() == 0)
                        return true;
                    else
                        return false;

                case "id": // search query

                    rs = stat.executeQuery("Select * From ETIQUETA WHERE TAG='" +
                            tag.getTag() + "'");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new Tag(rs.getInt("id"),
                                rs.getString("tag")));

                    Tag t = (Tag)archives.remove(0);

                    return t.getId();

                default:
                    return null;
            }
        }
        catch (SQLDataException exp)
        {
            System.out.println("SQL DATA ERROR: " + exp.getMessage());
        }
        catch (SQLException exp)
        {
            System.out.println("SQL ERROR: " + exp.getMessage());
        }
        catch (Exception exp) // General errors
        {
            System.out.println("ERROR! --> " + exp.getMessage());
        }

        return null;
    }*/

    // TAG ARTICLE CROSS TABLE
    // TODO: Discuss if really necessary
    public static void CreateTag(String tag){

        Tag tg = new Tag(tag);

        TagServices.getInstance().Create(tg);
    }

    // TODO: add Cross Table logic
    public static void ProcessTagsOnArticle(ArrayList<Tag> tags, Article article){

        List<Tag> tg = TagServices.getInstance().FindAll();

        if(tg == null)
            for (Tag tag:
                 tags) {
                TagServices.getInstance().Create(tag);
            }
        else
            for (Tag tag:
                 tags) {
                Tag bubble = TagServices.getInstance().Find(tag.getTag());

                if(bubble == null)
                    TagServices.getInstance().Create(tag);
            }

        /*int count = 0;

        for (Tag tag:
             tags){

            if ((boolean) TagQuery(tag, "isNew"))
                TagQuery(tag, "insert");

            try {
                // Preparing to execute query
                Statement stat = conn.createStatement();
                ResultSet rs, rx;

                rx =stat.executeQuery("SELECT * FROM ETIQUETA WHERE TAG='" +
                        tag.getTag() + "'");

                while (rx.next())
                    tag = new Tag(rx.getInt("id"), tag.getTag());

                rs = stat.executeQuery("SELECT * FROM HASHTAG WHERE ETIQUETA=" +
                        tag.getId() + " AND ARTICULO=" +
                        article);

                while (rs.next())
                    count++;

                if (count == 0)
                    stat.executeUpdate("Insert Into HASHTAG (ETIQUETA, ARTICULO) Values(" +
                            tag.getId() + ", " +
                            article + ")");

            } catch (SQLDataException exp) {
                System.out.println("SQL DATA ERROR: " + exp.getMessage());
            } catch (SQLException exp) {
                System.out.println("SQL ERROR: " + exp.getMessage());
            } catch (Exception exp) // General errors
            {
                System.out.println("ERROR! --> " + exp.getMessage());
            }
        }*/
    }

    public static ArrayList<Tag> GetAllArticleTags(Article article){

        ArrayList<Tag> tg = new ArrayList<>();

        List<Tag> tags = TagServices.getInstance().FindAll();

        for (Tag tag:
             tags) {
            // Add Cross table logic
        }

        return tg;

        /*try {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            Statement stat2 = conn.createStatement();
            ResultSet rs;
            ResultSet rx;

            rs = stat.executeQuery("SELECT * FROM HASHTAG WHERE ARTICULO=" +
                    article);

            while (rs.next()) {

                rx = stat2.executeQuery("SELECT * FROM ETIQUETA WHERE ID =" +
                        rs.getInt("etiqueta"));
                while (rx.next())
                {
                    //System.out.println("Entered while-------------"+rs.getString("etiqueta"));
                    tags.add(new Tag(rs.getInt("etiqueta"), rx.getString("tag")));
                }

            }

        } catch (SQLDataException exp) {
            System.out.println("SQL DATA ERROR: " + exp.getMessage());
        } catch (SQLException exp) {
            System.out.println("SQL ERROR: " + exp.getMessage());
        } catch (Exception exp) // General errors
        {
            System.out.println("ERROR! --> " + exp.getMessage());
        }

        return tags;*/
    }

}
