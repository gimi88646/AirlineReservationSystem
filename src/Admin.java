import java.sql.SQLException;

public class Admin extends Person {
    Admin(){super("Anonymous");}
    Admin(String username){
        super(username);
    }

    // implementation of methods that are displayed in driver class
    //this method takes to-from time.. number of passengers for a carrier as parameters
    public void addRoute() throws SQLException {
        statement.execute("INSERT INTO Flights(flightId,travelTo,travelFrom) VALUES('120','Lahore','Peshawar');");
    }
    public void cancelRoute(String flightId) throws SQLException{
        String mostRecentBookingDate;
        /*
         * get bookForDate from the bookings table for a specific flightId and orderby bookedforDate LIMIT 1
         * mostRecentBookingDate = resultSet.geString(bookedForDate)
         * UPDATE Flights SET activeTill = 'mostRecentBookingDate' WHERE flightId = 'flightID'
         * */
        statement.execute("SELECT bookedForDate FROM Bookings WHERE flightId = '"+flightId+"' ORDER BY bookedForDate DESC LIMIT 1");
        mostRecentBookingDate = statement.getResultSet().getString("bookedForDate");
        statement.execute("UPDATE Flights SET activeTill = 'mostRecentBookingDate' WHERE flightId = '"+flightId+"'");
        UPDATE Customers
        SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'
        WHERE CustomerID = 1;
    }
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
}
