/**
 * Created by Siclait on 30/05/2016.
 */
import spark.Spark;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args){

        Spark.staticFileLocation("/public");
        
        // Initiate server connection
        //DatabaseManager.StartServer();

        // Terminate server connection
        //DatabaseManager.CloseServer();

        get("/", (req, res) -> "Home page");
    }
}
