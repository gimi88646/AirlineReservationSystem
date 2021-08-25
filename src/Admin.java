import javax.xml.transform.Result;
import java.awt.image.RescaleOp;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin extends Person {
    public static void main(String[] args) {
    }
    Admin(){super("Anonymous");}
    Admin(String username){
        super(username);
    }

    // implementation of methods that are displayed in driver class
    //this method takes to-from time.. number of passengers for a carrier as parameters
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
}
