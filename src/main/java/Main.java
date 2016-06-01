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

    public static void main(String[] args) throws Exception {

        staticFileLocation("/public");
        DatabaseManager DBmanager = new DatabaseManager();
        DBmanager.BootUP();
        DBmanager.PrintData();
        PageCreator pages = new PageCreator();


        // Initiate server connection
        //DatabaseManager.StartServer();

        // Terminate server connection
        //DatabaseManager.CloseServer();
        
    }
}
