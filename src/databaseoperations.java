import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class databaseoperations{

    public static Connection connect(String databasesource) {
        Connection conn = null;
        try{
            Class.forName("org.sqlite.JDBC");
            String url = databasesource;
            conn = DriverManager.getConnection("jdbc:sqlite:"+url);
            System.out.println("Connection to database has been established");
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        catch(ClassNotFoundException cex){
            System.out.println(cex.getMessage());
        }
        return conn;
    }
    public static void senddata(Connection database, String datatosend){
        String query = datatosend;
        try{
            ResultSet stmt = 
            stmnt.executeUpdate();
        }
        catch(SQLException sqlexc){
            System.out.println(sqlexc.getMessage());
        }
    }
}