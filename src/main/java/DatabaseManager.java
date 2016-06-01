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

    public static Object SearchArchivesBy(String category){

        return ArticleQuery(new Article(), category);
    }

    // User Queries
    public static Object UserQuery(User user, String query) {

        try
        {
            // Preparing to execute query
            Statement stat = conn.createStatement();
            ResultSet rs;

            switch (query)
            {
                case "insert":

                    stat.execute("INSERT INTO USUARIO (USERNAME, NOMBRE, PASSWORD, ADMIN, AUTOR) VALUES (" + user.getUsername() + ", '" +
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
                        archives.add(new Article(rs.getInt("id"),
                                rs.getString("titulo"),
                                rs.getString("cuerpo"),
                                rs.getString("autor"),
                                rs.getDate("fecha"),
                                rs.getDate("modificado")));

                    return archives.size(); // flse is 0

                case "taken": // search query

                    rs = stat.executeQuery("Select * From USUARIO WHERE USERNAME='" +
                            user.getUsername() + "'");

                    archives = new ArrayList<>();

                    while(rs.next())
                        archives.add(new Article(rs.getInt("id"),
                                rs.getString("titulo"),
                                rs.getString("cuerpo"),
                                rs.getString("autor"),
                                rs.getDate("fecha"),
                                rs.getDate("modificado")));

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

/*
    public static void createBasicTable(Connection conn) throws SQLException {
        System.out.println("Creating table in given database...");
        Statement stmt = conn.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"( " +
                "matricula INTEGER NOT NULL, " +
                "nombre varchar(100) NOT NULL,  " +
                "apellidos varchar(100) NOT NULL, " +
                "telefono varchar(50) NOT NULL, " +
                "PRIMARY KEY (matricula))";

        stmt.executeUpdate(sql);
        System.out.println("Created table in given database...");
    }


    public static void createMainPage(Connection conn)
    {
        get("/", (req, res) -> {
            res.status(200);
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("message", "Welcome to BlogBoutIt! We can't wait to read BoutIt");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    }



    public static void createBasicTable(Connection conn) throws SQLException {
        System.out.println("Creating table in given database...");
        Statement stmt = conn.createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"( " +
                "matricula INTEGER NOT NULL, " +
                "nombre varchar(100) NOT NULL,  " +
                "apellidos varchar(100) NOT NULL, " +
                "telefono varchar(50) NOT NULL, " +
                "PRIMARY KEY (matricula))";

        stmt.executeUpdate(sql);
        System.out.println("Created table in given database...");
    }


    public static void createMainPage(Connection conn)
    {
        get("/", (req, res) -> {
            res.status(200);
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("message", "Welcome to BlogBoutIt! We can't wait to read BoutIt");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    }
    */
}
