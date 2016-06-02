/**
 * Created by Siclait on 30/05/2016.
 */
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
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
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        DatabaseManager DBmanager = new DatabaseManager();
        DBmanager.BootUP();
        DBmanager.PrintData();
        PageCreator pages = new PageCreator();
        //DBmanager.DeleteUser("yolo");
        System.out.println(DBmanager.isUsernameTaken("yolo"));
        // Initiate server connection
        //DatabaseManager.StartServer();

        // Terminate server connection
        //DatabaseManager.CloseServer();
        
    }

    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
