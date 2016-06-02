/**
 * Created by Siclait on 30/05/2016.
 */
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import spark.ModelAndView;
import spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) throws Exception {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        PageCreator pages = new PageCreator();
        // Initiate server connection
        //DatabaseManager.StartServer();
        //java.util.Date utilDate = new java.util.Date();
        //java.sql.Date time = new java.sql.Date(utilDate.getTime());
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
