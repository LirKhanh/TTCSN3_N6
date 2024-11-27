
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectJDBCUtil {
    private static String url  ="jdbc:mysql://localhost:3306/fs?autoReconnect=True&useSSL=false";
    private static String name="root";
    private static String pass="1234";

    public static Connection getConnection()
    {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, name, pass);
        }
        catch(SQLException e)
        {
            System.out.print(e.getMessage());
        }
        return conn;
    }
}