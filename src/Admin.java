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
    public void addRoute(ArrayList<String> flightinfo) throws Exception {

        String query = "'" + flightinfo.get(0) + "'";

        for (int i = 1; i <= flightinfo.size() - 1; i++) {
            String infopiece = "," + "'" + flightinfo.get(i) + "'";
            query += infopiece;

        }
        statement.execute(
                "INSERT INTO Flights(flightId,travelTo,travelFrom,numberOfBcatSeats,numberOfEcatSeats,takeOffTime) VALUES("
                        + query + ")");
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

    public void cancelFlight( String flightId,String date, String reason) throws SQLException{
        String notification = "Dear user your Booking for Flight "+flightId+ " has been cancelled due to "+reason;
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
        // String createTableSql = "CREATE TABLE IF NOT EXISTS CancelledFlights (" +
        //         "cancellationId INTEGER NOT NULL UNIQUE," +
        //         "flightId INTEGER," +
        //         "cancelledDate date," +"updatedby TEXT,"+
        //         "PRIMARY KEY(cancellationId AUTOINCREMENT)," +
        //         "FOREIGN KEY(flightId) REFERENCES Flights(flightId)" +
        //         ");";
        // statement.execute(createTableSql);

        statement.execute("INSERT INTO CancelledFlights(updatedby, flightId,cancelledDate) VALUES("+ username +","+
                flightId+",'" +date+
                "')");

        // String createNotificationTableSQL ="CREATE TABLE IF NOT EXISTS notifications (" +
        //         "notificationId INTEGER NOT NULL UNIQUE," +
        //         "notification TEXT," +
        //         "username TEXT," +
        //         "PRIMARY KEY(notificationId AUTOINCREMENT)," +
        //         "FOREIGN KEY (username) REFERENCES Users(username)" +
        //         ");" ;

        // statement.execute(createTableSql);
        // statement.execute(createNotificationTableSQL);
        /*
        * select usernames from bookings .. where bookedForDate = date and flightId = flightId
        * or tamam usernames ko notification table me dal kar is method se msg bhejna he
        * */
        statement.execute("SELECT bookedby FROM Bookings WHERE bookedForDate = '"+date+"' AND FlightId ='"+flightId+"' ");
        ResultSet resultSet = statement.getResultSet();

        ArrayList<String> usernames =  new ArrayList();

        while(resultSet.next()){
            usernames.add(resultSet.getString("bookedBy"));
        }
        for(String username: usernames){
            statement.executeUpdate("INSERT INTO notifications(username,notification) VALUES('" +
                    username+"','"+notification+
                    "')");
        }

        /*
        * tamam usernames nikalo jinki bookings iss flightId me hain
        * then unhen ye msg apki flightId XXX has been cancelled due to + reason
        *
        * */

        /*UPDATE
        agent1
        SET commission=commission+.02
        WHERE 2>=(
        SELECT COUNT(cust_code) FROM customer
        WHERE customer.agent_code=agent1.agent_code);*/

        /*update notifications
        * set notification = notification
        * username=
        * */

    }
}
// 001,washington DC,New York,20,30,3:55:00
//