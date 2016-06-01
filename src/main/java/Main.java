/**
 * Created by Siclait on 30/05/2016.
 */
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

    public static void main(String[] args) throws Exception{


        staticFileLocation("/public");
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



}
