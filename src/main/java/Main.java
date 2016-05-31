/**
 * Created by Siclait on 30/05/2016.
 */
public class Main {

    public static void main(String[] args){
        // Initiate server connection
        DatabaseManager.StartServer();

        // Terminate server connection
        DatabaseManager.CloseServer();
    }
}
