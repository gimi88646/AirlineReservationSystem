import javax.xml.transform.Result;
import java.awt.image.RescaleOp;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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


    // admin wants to cancel a flight for a date..

    public void cancelFlight( String flightId,String date, String message) throws SQLException{
        String notification = "Your Booking for Flight "+flightId+ " has been cancelled!\n " + message;
        /*
        * when admin cancels a flight for a day..
        * it gets inserted to cancelled flights as date with
        * and the user should not be able to see that flight if the flight is in cancellation table, for that date..  */
        /*
        * if flight already has some bookings from this method we will notify the user that his booking for a flight has been cancelled...
        *
        * */
//        statement.execute("CREATE TABLE IF NOT EXISTS CancelledFlights(" +
//                "flightID INTEGER," +
//                        "cancelledDate date" +
//                "FOREGIN KEY flightId"+
//                // some columns
//                ");");
        String createCancelledFlightsTableSql = "CREATE TABLE IF NOT EXISTS CancelledFlights (" +
                "cancellationId INTEGER NOT NULL UNIQUE," +
                "flightId INTEGER," +
                "cancelledDate date," +
                "whenCancelled datetime,"+
                "cancelledBy TEXT,"+
                "PRIMARY KEY(cancellationId AUTOINCREMENT)," +
                "FOREIGN KEY(cancelledBy) REFERENCES users(username),"+
                "FOREIGN KEY(flightId) REFERENCES Flights(flightId)" +
                ");";

        String createNotificationTableSQL ="CREATE TABLE IF NOT EXISTS notifications (" +
                "notificationId INTEGER NOT NULL UNIQUE," +
                "notification TEXT," +
                "username TEXT," +
                "whenNotified datetime,"+
                "PRIMARY KEY(notificationId AUTOINCREMENT)," +
                "FOREIGN KEY (username) REFERENCES Users(username)" +
                ");" ;

        statement.execute(createCancelledFlightsTableSql);
        statement.execute(createNotificationTableSQL);

        //flightId and date of cancellation gets added to CancelledFlights table..
        // advantage -> the user wont be able to see the flightId happens to exists in the cancellations table
        statement.execute("INSERT INTO CancelledFlights(flightId,cancelledDate,cancelledBy,whenCancelled) VALUES("+
                flightId+",'" +date+ "','"+username+
                "',datetime('now'))");
        //get the users who have booked flight-being-cancelled on date admin has given.
        statement.execute("SELECT bookedBy FROM Bookings WHERE bookedForDate = '"+date+"' AND FlightId ='"+flightId+"' ");
        ResultSet resultSet = statement.getResultSet();
        ArrayList<String> usernames =  new ArrayList();
        while(resultSet.next()){
            usernames.add(resultSet.getString("bookedBy"));
        }

        // send notification to users who had booked the cancelled flight
        for(String username: usernames){
            statement.executeUpdate("INSERT INTO Notifications(username,notification,whenNotified) VALUES('" +
                    username+"','"+notification+
                    "',datetime('now'))");
        }
        //rows  bookings table gets deleted. where conditions get true
        statement.execute("DELETE FROM Bookings WHERE flightId ="+flightId+" AND bookedForDate = '"+date+"';");


    }
}
