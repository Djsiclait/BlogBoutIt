/**
 * Created by Siclait on 30/05/2016.
 */
import spark.ModelAndView;
import spark.Spark;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args){

        staticFileLocation("/public");

        // Initiate server connection
        //DatabaseManager.StartServer();

        // Terminate server connection
        //DatabaseManager.CloseServer();

        get("/", (req, res) -> {
            res.status(200);
            Map<String, Object> attributes = new HashMap<>();

            attributes.put("message", "Welcome to BlogBoutIt! We can't wait to read BoutIt");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    }
}
