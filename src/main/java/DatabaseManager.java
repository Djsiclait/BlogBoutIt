import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.rmi.ConnectException;
import java.sql.*;
import org.h2.tools.Server;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

/**
 * Created by Siclait on 30/05/2016.
 */
public class DatabaseManager {

    // Attributes
    private static Connection conn;
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:tcp://localhost:9092/~/Blog;IFEXISTS=TRUE";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private static ArrayList<Object> archives;

    public DatabaseManager() throws ClassNotFoundException, SQLException {
        try {
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

    }


    public static void BootUP() throws Exception
    {

            try {
                Statement stat = conn.createStatement();

                System.out.println(stat.toString());
                ResultSet rs = stat.executeQuery("SELECT * FROM USUARIO");

                if (rs.getFetchSize() == 0) {
                    stat.executeUpdate("INSERT INTO USUARIO Values ('admin', 'Administrator', 'admin', 1, 1)");
                    System.out.println("Admin created");
                }

            } catch (SQLDataException exp) {
                System.out.println("Data ERROR! --> " + exp.getMessage());
            } catch (SQLException exp) {
                System.out.println("SQL ERROR! --> " + exp.getMessage());
            } catch (Exception exp) {
                System.out.println("General ERROR! --> " + exp.getMessage());
            }
    }

    public static Connection getConn() {
        return conn;
    }

    public static void PrintData()
    {
        try {
            Statement stat = conn.createStatement();

            System.out.println(stat.toString());
            ResultSet rs = stat.executeQuery("SELECT * FROM USUARIO");
             while (rs.next())
             {
                System.out.println(rs.getString("USERNAME")+rs.getString("NOMBRE"));
             }

        } catch (SQLDataException exp) {
            System.out.println("Data ERROR! --> " + exp.getMessage());
        } catch (SQLException exp) {
            System.out.println("SQL ERROR! --> " + exp.getMessage());
        } catch (Exception exp) {
            System.out.println("General ERROR! --> " + exp.getMessage());
        }
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


    // Basic Query Functions
    private static Object ArticleQuery(Article article, String query)
    {
        try
        {


            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            switch (query)
            {
                case "insert":

                    stat.execute("INSERT INTO ARTICULO (ID, TITULO, CUERPO, AUTOR, FECHA, MODIFICADO) VALUES (" + article.getId() + ", '" +
                            article.getTitle() + "', '" +
                            article.getBody() + "', '" +
                            article.getAuthor() + "', '" +
                            article.getPubDate() + "', '" +
                            article.getLastEdit() +"')");

                    return null;

                case "delete":

                    stat.execute("DELETE FROM ARTICULO WHERE ID=" + article.getId());

                    return null;

                case "edit":

                    stat.execute("UPDATE ARTICULO SET TITULO='" +
                            article.getTitle() + "' , CUERPO='" +
                            article.getBody() + "', MODIFICADO='"  +
                            article.getLastEdit() + "' WHERE ID=" +
                            article.getId());

                    return null;

                case "list": // search query

                    rs = stat.executeQuery("Select * From ARTICULO");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new Article(rs.getInt("id"),
                                rs.getString("titulo"),
                                rs.getString("cuerpo"),
                                rs.getString("autor"),
                                rs.getDate("fecha"),
                                rs.getDate("modificado")));

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
                                        rs.getString("autor"),
                                        rs.getDate("fecha"),
                                        rs.getDate("modificado")));

                            return archives.remove(0);

                        case "title":

                            rs = stat.executeQuery("Select * From ARTICULO Where TITULO='" + article.getTitle() + "'");

                            archives = new ArrayList<>();

                            while(rs.next())
                                archives.add(new Article(rs.getInt("id"),
                                        rs.getString("titulo"),
                                        rs.getString("cuerpo"),
                                        rs.getString("autor"),
                                        rs.getDate("fecha"),
                                        rs.getDate("modificado")));

                            return archives;

                        case "author":

                            rs = stat.executeQuery("Select * From ARTICULO Where AUTOR='" + article.getAuthor() + "'");

                            archives = new ArrayList<>();

                            while(rs.next())
                                archives.add(new Article(rs.getInt("id"),
                                        rs.getString("titulo"),
                                        rs.getString("cuerpo"),
                                        rs.getString("autor"),
                                        rs.getDate("fecha"),
                                        rs.getDate("modificado")));

                            return archives;

                        case "pubDate":

                            rs = stat.executeQuery("Select * From ARTICULO Where TITULO='" + article.getPubDate() + "'");

                            archives = new ArrayList<>();

                            while(rs.next())
                                archives.add(new Article(rs.getInt("id"),
                                        rs.getString("titulo"),
                                        rs.getString("cuerpo"),
                                        rs.getString("autor"),
                                        rs.getDate("fecha"),
                                        rs.getDate("modificado")));

                            return archives;

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

    public static void CreateArticle(int id, String title, String body, String author, Date pubDate, Date lastEdit){

        Article article = new Article(id, title, body, author, pubDate, lastEdit);

        ArticleQuery(article, "insert");
    }

    public static void DeleteArticle(int id){
        Article article = new Article(id);
        ArticleQuery(article, "delete");
    }

    public static void EditArticle(int id, String title, String body, Date lastEdit){

        Article article = new Article(id, title, body, lastEdit);

        ArticleQuery(article, "edit");
    }

    public static ArrayList<Article> SearchArchivesBy(String category){

        return (ArrayList<Article>) ArticleQuery(new Article(), category);
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
                            user.isAuthor()  + " WHERE USERNAME=" +
                            user.getUsername());

                    return null;

                case "isAdmin": // search query

                    rs = stat.executeQuery("Select * From USUARIO WHERE USERNAME='" +
                            user.getUsername() + "' AND ADMIN=1");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new User(rs.getString("username"),
                                rs.getString("nombre"),
                                rs.getString("password"),
                                rs.getBoolean("amin"),
                                rs.getBoolean("author")));

                    return archives.size(); // flse is 0

                case "taken": // search query

                    rs = stat.executeQuery("Select * From USUARIO WHERE USERNAME='" +
                            user.getUsername() + "'");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new User(rs.getString("username"),
                                rs.getString("nombre"),
                                rs.getString("password"),
                                rs.getBoolean("amin"),
                                rs.getBoolean("author")));

                    return archives.size(); // false if 0

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

    public static void CreateUser(String username, String name, String password, boolean admin, boolean author){

        User user = new User(username, name, password, admin, author);

        UserQuery(user, "insert");
    }

    public static void DeleteUser(String username){

        User user = new User(username);

        UserQuery(user, "delete");
    }

    public static void MakeAdmin(String username, String name, String password){

        User user = new User(username, name, password, true, true);

        UserQuery(user, "edit");
    }

    public static void EditUser(String username, String name, String password, boolean admin, boolean author){

        User user = new User(username, name, password, admin, author);

        UserQuery(user, "edit");
    }

    public static boolean isAdmin(String username){

        User user = new User(username);

        return (boolean)UserQuery(user, "isAdmin");
    }

    // Commet Query
    private static Object CommentQuery(Comment comment, String query){

        try
        {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            switch (query)
            {
                case "insert":

                    stat.execute("INSERT INTO COMENTARIO (ID, COMMENT, AUTOR, ARTICULO, FECHA) VALUES (" + comment.getId() + ", '" +
                            comment.getComment() + "', '" +
                            comment.getAuthor() + "', " +
                            comment.getArticle() + ", " +
                            comment.getPubDate() + ")");

                    return null;

                case "delete":

                    stat.execute("DELETE FROM COMENTARIO WHERE ID=" +
                            comment.getId());

                    return null;

                case "comments": // search query

                    rs = stat.executeQuery("Select * From COMENTARIO WHERE ARTICLE=" +
                            comment.getArticle());

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new Comment(rs.getInt("id"),
                                rs.getString("comment"),
                                rs.getString("autor"),
                                rs.getInt("articulo"),
                                rs.getDate("fecha")));

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

    public static void CreatedComment(int id, String comment, String author, int article, Date date){

        Comment com = new Comment(id, comment, author, article, date);

        CommentQuery(com, "insert");
    }

    public static void DeleteComment(int id){

        Comment comment = new Comment(id);

        CommentQuery(comment, "delete");
    }

    public static ArrayList<Comment> GetArticleComments(int id, int article){

        Comment comment = new Comment(id, article);

        return (ArrayList<Comment>) CommentQuery(comment, "comments");
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

                    stat.execute("INSERT INTO ETIQUETA (ID, TAG) VALUES (" + tag.getId() + ", '" +
                            tag.getTag() + "')");

                    return null;

                case "isNew": // search query

                    rs = stat.executeQuery("Select * From ETIQUETA WHERE TAG='" +
                            tag.getTag() + "'");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new Tag(rs.getInt("id"),
                                rs.getString("tag")));

                    return archives.size();

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

    // TAG ARTICEL CROSS TABLE
    public static void ProcessTagsOnArticlea(Tag tag, Article article){

        int count = 0;

        if((boolean)TagQuery(tag, "isNew"))
            TagQuery(tag, "insert");

        try
        {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            rs = stat.executeQuery("SELECT * FROM HASTAG WHERE ETIQUETA=" +
                    tag.getId() + " AND ARTICULO=" +
                    article.getId());

            while(rs.next())
                count++;

            if(count == 0)
                stat.executeUpdate("Insert Into HASTAG (ETIQUETA, ARTICULO) Values(" +
                        tag.getId() + ", " +
                        article.getId() + ")");

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

    }


    public static void TestTatle(){
        try
        {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            //stat.execute("DROP TABLE COMENTARIO");
            //System.out.println("Drop Created");

            stat.executeUpdate("INSERT into Hashtag (ETIQUETA, ARTICULO) Values (0, 0)");
            stat.executeUpdate("INSERT into Hashtag (ETIQUETA, ARTICULO) Values (1, 0)");

            rs = stat.executeQuery("SELECT * FROM hashtag");

            while(rs.next())
                System.out.println(rs.getInt("etiqueta") + " " + rs.getInt("articulo"));

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
    }


}
