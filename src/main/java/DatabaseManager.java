import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import java.rmi.ConnectException;
import java.sql.*;
import org.h2.tools.Server;
import java.sql.Statement;
import java.sql.ResultSet;
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

    private DatabaseManager(){

    }

    public static void BootUP() throws Exception
    {
        if(StartServerConnection() != null)
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

    public static Connection StartServerConnection()
    {
        try {
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            System.out.println("Connection Successful!");

            return conn;
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

        return null;
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
