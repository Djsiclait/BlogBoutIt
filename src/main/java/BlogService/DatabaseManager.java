/**
 * Created by Siclait on 30/05/2016.
 */
package BlogService;

import Entity.*;

import org.h2.tools.Server;

import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

            System.out.println("Admin Created Successfully!");
        }
        else
            System.out.println("Database already configured");

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

    public static void CloseServerConnection(){

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
    }

/*
    // Basic Query Functions
    private static Object ArticleQuery(Article article, String query){

        try
        {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            switch (query)
            {
                case "insert":

                    stat.execute("INSERT INTO ARTICULO (TITULO, CUERPO, AUTOR) VALUES ('" +
                            article.getTitle() + "', '" +
                            article.getBody() + "', '" +
                            article.getAuthor() + "')");

                    rs = stat.executeQuery("Select ID From ARTICULO Where CUERPO ='" + article.getBody() + "'");

                    int id = -1;

                    while (rs.next())
                        id = rs.getInt("id");

                    return id;

                case "delete":

                    stat.execute("DELETE FROM ARTICULO WHERE ID=" + article.getId());

                    return null;

                case "edit":

                    stat.execute("UPDATE ARTICULO SET TITULO='" +
                            article.getTitle() + "' , CUERPO='" +
                            article.getBody() + "' WHERE ID=" +
                            article.getId());

                    return null;

                case "list": // search query

                    rs = stat.executeQuery("Select * From ARTICULO");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new Article(rs.getInt("id"),
                                rs.getString("titulo"),
                                rs.getString("cuerpo"),
                                rs.getString("autor")));

                    return archives;

                default: // specific search query

                    switch (query)
                    {
                        case "id":

                            rs = stat.executeQuery("Select * From ARTICULO Where ID=" + article.getId());

                            archives = new ArrayList<>();

                            while(rs.next())
                                archives.add(new Article(rs.getInt("id"),
                                        rs.getString("titulo"),
                                        rs.getString("cuerpo"),
                                        rs.getString("autor")));

                            return archives;

                        case "title":

                            rs = stat.executeQuery("Select * From ARTICULO Where TITULO='" + article.getTitle() + "'");

                            archives = new ArrayList<>();

                            while(rs.next())
                                archives.add(new Article(rs.getInt("id"),
                                        rs.getString("titulo"),
                                        rs.getString("cuerpo"),
                                        rs.getString("autor")));

                            return archives;

                        case "author":

                            rs = stat.executeQuery("Select * From ARTICULO Where AUTOR='" + article.getAuthor() + "'");

                            archives = new ArrayList<>();

                            while(rs.next())
                                archives.add(new Article(rs.getInt("id"),
                                        rs.getString("titulo"),
                                        rs.getString("cuerpo"),
                                        rs.getString("autor")));

                            return archives;

                        case "pubDate": // Temporarily shutdown

                            //rs = stat.executeQuery("Select * From ARTICULO Where TITULO='" + article.getPubDate() + "'");

                            //archives = new ArrayList<>();

                            //while(rs.next())
                                //archives.add(new Article(rs.getInt("id"),
                                        //rs.getString("titulo"),
                                        //rs.getString("cuerpo"),
                                        //rs.getString("autor")));

                            //return archives;
                            return null;

                        default:
                            return null;
                    }
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
    }

    public static int CreateArticle(String title, String body, String author){

        Article article = new Article(0, title, body, author);

        return (int)ArticleQuery(article, "insert");
    }

    public static void DeleteArticle(int id){

        Article article = new Article(id);

        ArticleQuery(article, "delete");
    }

    public static void EditArticle(int id, String title, String body){

        Article article = new Article(id, title, body);

        ArticleQuery(article, "edit");
    }

    public static ArrayList<Article> SearchArchivesBy(Article article, String category){

        return (ArrayList<Article>) ArticleQuery(article, category);
    }

    public static ArrayList<Article> GetAllArticles(){

        return (ArrayList<Article>) ArticleQuery(new Article(), "list");
    }

    // User Queries
    private static Object UserQuery(User user, String query) {

        try
        {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            switch (query)
            {
                case "insert":

                    stat.execute("INSERT INTO USUARIO (USERNAME, NOMBRE, PASSWORD, ADMIN, AUTOR) VALUES ('" + user.getUsername() + "', '" +
                            user.getName() + "', '" +
                            user.getPassword() + "', " +
                            user.isAdmin() + ", " +
                            user.isAuthor() + ")");

                    return null;

                case "delete":

                    stat.execute("DELETE FROM USUARIO WHERE USERNAME='" +
                            user.getUsername() + "' AND ADMIN=0");

                    return null;

                case "edit":

                    stat.execute("UPDATE USUARIO SET NOMBRE='" +
                            user.getName() + "' , PASSWORD='" +
                            user.getPassword() + "', ADMIN="  +
                            user.isAdmin() + ", AUTOR="  +
                            user.isAuthor()  + " WHERE USERNAME='" +
                            user.getUsername() + "'");

                    return null;

                case "isAdmin": // search query

                    rs = stat.executeQuery("Select * From USUARIO WHERE USERNAME='" +
                            user.getUsername() + "' AND ADMIN=1");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new User(rs.getString("username"),
                                rs.getString("nombre"),
                                rs.getString("password"),
                                rs.getBoolean("admin"),
                                rs.getBoolean("autor")));

                    return archives.size(); // flse is 0

                case "taken": // search query

                    rs = stat.executeQuery("Select * From USUARIO WHERE USERNAME='" +
                            user.getUsername() + "'");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new User(rs.getString("username"),
                                rs.getString("nombre"),
                                rs.getString("password"),
                                rs.getBoolean("admin"),
                                rs.getBoolean("autor")));

                    return archives.size(); // false if 0

                default: // user search query

                    rs = stat.executeQuery("Select * From USUARIO WHERE USERNAME='" +
                            user.getUsername() + "'");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new User(rs.getString("username"),
                                rs.getString("nombre"),
                                rs.getString("password"),
                                rs.getBoolean("admin"),
                                rs.getBoolean("autor")));

                    return archives.remove(0); // returns the user
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
    }

    public static void CreateUser(String username, String name, String password, boolean admin, boolean author){

        User user = new User(username, name, password, admin, author);

        if(!isUsernameTaken(username))
            UserQuery(user, "insert");
    }

    public static boolean isUsernameTaken(String username) {

        User user = new User(username, "x", "x", false, false);

        if((int)UserQuery(user, "taken") == 0)
            return false;
        else
            return true;
    }

    public static void DeleteUser(String username){

        User user = new User(username);

        UserQuery(user, "delete");
    }

    public static void MakeAdmin(String username, String name, String password){

        EditUser(username, name, password, true, true);
    }

    public static void EditUser(String username, String newName, String newPassword, boolean admin, boolean author){

        User user = new User(username, newName, newPassword, admin, author);

        UserQuery(user, "edit");
    }

    public static boolean isAdmin(String username){

        User user = new User(username);

        if((int)UserQuery(user, "isAdmin") != 0)
            return true;
        else
            return false;
    }

    public static boolean CheckCredentials(String username, String password){

        try{

            ResultSet rs = conn.createStatement().executeQuery("Select * From USUARIO Where USERNAME='" +
                    username + "' AND PASSWORD='" +
                    password + "'");

            ArrayList<User> users = new ArrayList<>();

            while(rs.next())
                users.add(new User(rs.getString("username"),
                        rs.getString("password")));
            if(users.size() > 0)
                return true;
            else
                return false;
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

        return false;
    }

    public static User FetchUser(String username){

        User user = new User(username);

        return (User)UserQuery(user, username);

    }

    // Comment Query
    private static Object CommentQuery(Comment comment, String query){

        try
        {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            switch (query)
            {
                case "insert":

                    stat.execute("INSERT INTO COMENTARIO (COMMENT, AUTOR, ARTICULO) VALUES ('" +
                            comment.getComment() + "', '" +
                            comment.getAuthor() + "', " +
                            comment.getArticle() + ")");

                    return null;

                case "delete":

                    stat.execute("DELETE FROM COMENTARIO WHERE ID=" +
                            comment.getId());

                    return null;

                case "comments": // search query

                    rs = stat.executeQuery("Select * From COMENTARIO WHERE ARTICULO=" +
                            comment.getArticle());

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new Comment(rs.getInt("id"),
                                rs.getString("comment"),
                                rs.getString("autor"),
                                rs.getInt("articulo")));

                    return archives;

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
    }

    public static void CreateComment(String comment, String author, int article){

        Comment com = new Comment(0, comment, author, article);

        CommentQuery(com, "insert");
    }

    public static void DeleteComment(int id){

        Comment comment = new Comment(id);

        CommentQuery(comment, "delete");
    }

    public static ArrayList<Comment> GetArticleComments(int article){

        Comment comment = new Comment(0, article);

        return (ArrayList<Comment>) CommentQuery(comment, "comments");
    }

    public static ArrayList<Comment> GetAllComments(){

        try{

            ResultSet rs = conn.createStatement().executeQuery("Select * From COMENTARIO");

            ArrayList<Comment> comments = new ArrayList<>();

            while(rs.next())
                comments.add(new Comment(rs.getInt("id"),
                        rs.getString("comment"),
                        rs.getString("autor"),
                        rs.getInt("articulo")));

            return comments;
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
    }

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
    }

    // TAG ARTICLE CROSS TABLE
    public static void ProcessTagsOnArticle(ArrayList<Tag> tags, int article){

        int count = 0;

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
        }
    }

    public static ArrayList<Tag> GetAllArticleTags(int article){

        ArrayList<Tag> tags = new ArrayList<>();

        try {
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

        return tags;
    }
*/
}
