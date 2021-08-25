<<<<<<< HEAD
import java.util.ArrayList;
=======
import javax.xml.transform.Result;
import java.awt.image.RescaleOp;
import java.sql.ResultSet;
import java.sql.SQLException;
>>>>>>> 0cd97e9cc82812130aa5217d60c5daa4786a028d

public class Admin extends Person {

    Admin(){super("Anonymous");}
    Admin(String username){
        super(username);
    }

    // implementation of methods that are displayed in driver class
    //this method takes to-from time.. number of passengers for a carrier as parameters
<<<<<<< HEAD
    public void addRoute(ArrayList<String> flightinfo){
        for (int i=0; i<=flightinfo.size(); i++){
            String flight = flightinfo.get(i);
            
        }
    }
    public void cancelRoute(){}
    //some more methods
    /*
    * flight ke database ke andar ek column he inactiveSince ka column hoga, jsme date us waqt aegi jab admin us particular flghtId ko inactive krdega
    * ab ye flight srf us din ki flights complete karegi, jis dino ki booking sme initialize ho chuki he
    * or koi naye din ki booking nahi legi
    * jese hii tamam flights complete karegii.. phr databse se dlete hojaegi
    * active till kaa column ...
    * select bookedForDate from bookings whereflightId  ORDER By bookedforDate DESC LIMIT 1
    * no more bookings on new date.. that is if any date has at least 1 booking it can take passengers
    *
    * js flight ko inactive kiya
    * */
=======
    public void addRoute() throws SQLException {
        statement.execute("INSERT INTO Flights(flightId,travelTo,travelFrom) VALUES('120','Lahore','Peshawar');");
    }
    public String cancelRoute(String flightId) throws SQLException{
        /*
        SELECT *
        FROM flights
        WHERE activeTill IS NULL;
*/
        /*
         * get bookedForDate from the bookings table for a specific flightId and orderby bookedforDate LIMIT 1
         * mostRecentBookingDate = resultSet.geString(bookedForDate)
         * UPDATE Flights SET activeTill = 'mostRecentBookingDate' WHERE flightId = 'flightID'
         * */
        String mostRecentlyBookedDate=null;
        statement.execute("SELECT bookedForDate FROM Bookings WHERE flightId = '"+flightId+"' AND bookedForDate>=date('now') ORDER BY bookedForDate DESC LIMIT 1");
        ResultSet resultSet = statement.getResultSet();
//        resultSet.wasNull(); this can come in handy
//  this works as well but if resultset is empty, things will not work out well      UPDATE Flights SET activeTill=(SELECT bookedForDate FROM Bookings WHERE flightId = '112' AND bookedForDate>=date('now') ORDER BY bookedForDate DESC LIMIT 1) WHERE flightId='112'

        // we also should check if the route is already cancelled.. for that activeTill column wont be null

        if(resultSet.next()){
            mostRecentlyBookedDate = resultSet.getString("bookedForDate");
            statement.execute("UPDATE Flights SET activeTill = '"+mostRecentlyBookedDate+"' WHERE flightId = '"+flightId+"';");
        }else {
            statement.execute("DELETE FROM Flights WHERE FlightId='"+flightId+"';");
        }
        return mostRecentlyBookedDate;
    }
>>>>>>> 0cd97e9cc82812130aa5217d60c5daa4786a028d
}
