package Database;

import java.sql.*;

import static java.lang.Class.forName;

public class connection {
    public static final String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String username="ahmed";
    public static final String password="12345";
    public static final String url="jdbc:sqlserver://localhost;database=company";
    static Connection con;
    public static Connection open()
    {
        try{
            Class.forName(driver);
             con = DriverManager.getConnection(url, username, password);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return con;
    }
    public static void close()
    {
        try {
            con.close();
            if (con.isClosed())
                System.out.println("Connection closed.");
        }
     catch (Exception e) {
        e.printStackTrace();
    }
    }
}
