import java.rmi.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Siclait on 30/05/2016.
 */
public class DatabaseManager {

    // Attributes
    private static Connection conn;

    private DatabaseManager(){

    }

    public static void StartServer()
    {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Blog;IFEXISTS=TRUE", "sa", "");
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

    public static void CloseServer(){

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
}
