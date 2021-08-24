import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class databaseoperations {

    public static Connection connect(String databasesource) throws Exception {
        Connection conn = null;
        Class.forName("org.sqlite.JDBC");
        String url = databasesource;
        conn = DriverManager.getConnection("jdbc:sqlite:" + url);
        System.out.println("Connection to database has been established");
        return conn;
    }

    public static void senddata(Connection database, String datatosend) {
        String query = datatosend;
        try {
            PreparedStatement stmt = database.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException sqlexc) {
            System.out.println(sqlexc.getMessage());
        }
    }
    public static void close(Connection db){
        try{
            db.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}