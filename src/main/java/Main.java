/**
 * Created by Siclait on 30/05/2016.
 */
import org.h2.tools.Server;
import spark.ModelAndView;
import spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {


    private static final String DB_DRIVER = "org.h2.Driver";
    private static String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";


    public static void main(String[] args) throws Exception{


        staticFileLocation("/public");
        // start the TCP Server
        Server server = Server.createTcpServer(args).start();
        System.out.println(server.getURL());
        System.out.println(server.getService());
        System.out.println(server.getStatus());
        Class.forName(DB_DRIVER);

        Connection conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        createBasicTable(conn);
        createMainPage(conn);

        // Initiate server connection
        //DatabaseManager.StartServer();

        // Terminate server connection
        //DatabaseManager.CloseServer();

    }


    public static void createBasicTable(Connection conn) throws SQLException {
        System.out.println("Creating table in given database...");
        //Creating Article
        Statement createArticle = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS Article( " +
                "id INTEGER NOT NULL, " +
                "title varchar(1000) NOT NULL,  " +
                "body varchar(100000) NOT NULL, " +
                "author varchar(50) NOT NULL, " +
                "date TIMESTAMP NOT NULL, " +
                "PRIMARY KEY (id))";
        createArticle.executeUpdate(sql);

        Statement createComment = conn.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS comment( " +
                "id INTEGER NOT NULL, " +
                "comment varchar(100) NOT NULL,  " +
                "author varchar(100) NOT NULL, " +
                "articleID INTEGER NOT NULL, " +
                "date TIMESTAMP NOT NULL, " +
                "PRIMARY KEY (id))";
        createComment.executeUpdate(sql);

        Statement createUser = conn.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS User( " +
                "id INTEGER NOT NULL, " +
                "username varchar(100) NOT NULL,  " +
                "password varchar(100) NOT NULL, " +
                "admin boolean NOT NULL, " +
                "autor boolean NOT NULL, " +
                "PRIMARY KEY (id))";
        createUser.executeUpdate(sql);

        Statement createTag = conn.createStatement();
        sql = "CREATE TABLE IF NOT EXISTS Tag( " +
                "id INTEGER NOT NULL, " +
                "tag varchar(50) NOT NULL,  " +
                "PRIMARY KEY (id))";
        createTag.executeUpdate(sql);




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

    public static void createDataStructuresDatabase(Connection conn)
    {

    }



}
