import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class User extends Person {



    // this attribute will be useful in Airline class because we will be able to check if user is a member
    // if is a member the program will provide the user extra functionality
    // extra functionality  = history , view bookings, cancel bookings.

//    private boolean isSignedIn = false;

    //the user object is created when the program starts with default values (Anonymous)
    //if user wants to sign in. login method gets called
    User(){
        super("Anonymous");
    }

    //in user class there also should be some history so the user can see his previous history
    User(String username){
        super(username);
    }

    //if found it reassign the attributes of User Object and provide extra functionality
    // extra functionality  = history , view bookings, cancel bookings.


    public void book(ArrayList<String[]> passengers, ArrayList bookingInfo) throws SQLException{
//        date =bookinginfo[0]
//         number of passegers = passengers.size
        String date= (String) bookingInfo.get(0);
        char seatType = (char) bookingInfo.get(2);
        String flightId =(String) bookingInfo.get(3);

        //this method is to insert data into bookings table..  after data insertion the operation is complete

        // arraylist banani he order ke hisab se ..
        // phr wo info insert query laga kr database me save krni he .. khatam kahani....
        passengers.forEach((passenger)->{
            try {
                statement.execute("INSERT INTO " +
                        "Bookings(flightId,bookedOnDate,bookedForDate,bookedBy,fullName,cnic,seatType) VALUES(" +
                        flightId +","+
                        "date('now'),"+
                        date+","+
                        username+","+
                        passenger[0]+","+
                        passenger[1]+","+
                        seatType+","+
                        ")");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

    }
    public ResultSet getBookings() throws SQLException{
        //iske andar query hogi jo user k wali bookings greater than or equals to hogi aaj ki date se
        //ye method return karega resultset.. jisko process karega driver
        //
        //statement.execute();
        // this method returns a resultSet:  bookings made only by a username and are greater or equals to today

//        SELECT BOOKINGS.*, Flights.travelTo, Flights.travelFrom
//        FROM BOOKINGS
//        INNER JOIN Flights ON BOOKINGS.flightId= Flights.flightId
//        WHERE BOOKINGS.bookedBy ='gimi88646'
//        ORDER BY bookedForDate;

        statement.execute("SELECT Bookings.*,Flights.travelTo,Flights.travelFrom,Flights.takeOffTime FROM Bookings " +
                "INNER JOIN Flights ON Bookings.flightId=Flights.flightId " +
                "WHERE Bookings.bookedBy = '"+username+"' AND bookedForDate>= date('now') " +
                "ORDER BY bookedForDate");
        return statement.getResultSet();
//        SELECT * FROM BOOKINGS WHERE bookedBy='gimi88646' and bookedForDate>= date('now')
    }
    public void cancelBooking(int bookingId){
//        for this module getBookings will be called first in Driver then the user is asked to input the booking he wants to cancel
//        then that information is passed as parameter in this method and SQL gets in action .. not sure whether is should delete the booking or change the status to cancelled
//        this method first calls getBookigs and and results get displayed by the driver class
    }
    public ResultSet getHistory() throws SQLException{
        statement.execute("SELECT * FROM Bookings WHERE bookedBy = '"+username+"' bookedForDate<date('now')");
        return statement.getResultSet();
    }
}
