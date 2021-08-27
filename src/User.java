import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class User extends Person {
    // this attribute will be useful in Airline class because we will be able to check if user is a member
    // if is a member the program will provide the user extra functionality
    // extra functionality = history , view bookings, cancel bookings.

    // private boolean isSignedIn = false;

    // the user object is created when the program starts with default values
    // (Anonymous)
    // if user wants to sign in. login method gets called
    User() {
        super("Anonymous");
    }

    // in user class there also should be some history so the user can see his
    // previous history
    User(String username) {
        super(username);

    }

    // if found it reassign the attributes of User Object and provide extra
    // functionality
    // extra functionality = history , view bookings, cancel bookings.
    ArrayList<String> Updates = new ArrayList<String>();
    public void book(ArrayList<String[]> passengers, ArrayList bookingInfo) throws SQLException{
    //    date =bookinginfo[0]
    //     number of passegers = passengers.size
        String date= (String) bookingInfo.get(0);
        char seatType = (char) bookingInfo.get(2);
        String flightId =(String) bookingInfo.get(3);

        // //this method is to insert data into bookings table..  after data insertion the operation is complete

        // arraylist banani he order ke hisab se ..
        // phr wo info insert query laga kr database me save krni he .. khatam kahani....
        passengers.forEach((passenger)->{
            try {
                statement.execute("INSERT INTO " +
                        "Bookings(flightId,bookedOnDate,bookedForDate,bookedBy,fullName,cnic,seatType) VALUES(" +
                        flightId +","+
                        "date('now'),"+
                        "'"+date+"',"+
                        "'"+username+"',"+
                        "'"+passenger[0]+"',"+
                        "'"+passenger[1]+"',"+
                        "'"+seatType+
                        "')");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        String dateofbooking = (String) bookingInfo.get(0);
        char seattype = (char) bookingInfo.get(2);
        String flightid = (String) bookingInfo.get(3);
        try{
            Connection airlinedbconnect = databaseoperations.connect("D:\\Java Programs\\AirlineReservationSystem\\AirlineDatabase.db");
            Statement stmt = airlinedbconnect.createStatement();
            ResultSet countbookings = stmt.executeQuery("SELECT COUNT(*) FROM BOOKINGS");
            int bookingcount = 0;
            while(countbookings.next()){
                bookingcount = countbookings.getInt("count(*)");
            }
            //adding info into database from passengers array, passengers array consists of multiple arrays
            //that contains information of each passenger i.e, CNIC and NAME
            for(int i=0; i<=passengers.size(); i++){
                try{
                    stmt.executeUpdate("INSERT INTO BOOKINGS(bookingID, flightId, bookedOnDate, bookedForDate, bookedBy, fullName, cnic, seatType) VALUES("+bookingcount+", "+flightid+", "+"date('now')"+", "+dateofbooking+", "+username+", "+passengers.get(i)[0]+", "+passengers.get(i)[1]+", "+seattype+")");
                }
                catch(Exception sqlException){
                    System.out.println(sqlException.getMessage());
                }
            }
            
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }
    

    public ResultSet getBookings() throws SQLException {
        // iske andar query hogi jo user k wali bookings greater than or equals to hogi
        // aaj ki date se
        // ye method return karega resultset.. jisko process karega driver
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
        // SELECT * FROM BOOKINGS WHERE bookedBy='gimi88646' and bookedForDate>=
        // date('now')
    }
    public void cancelBooking(String bookingId) throws SQLException{
//        for this module getBookings will be called first in Driver then the user is asked to input the booking he wants to cancel
//        then that information is passed as parameter in this method and SQL gets in action .. not sure whether is should delete the booking or change the status to cancelled
//        this method first calls getBookigs and and results get displayed by the driver class
        statement.execute("DELETE FROM Bookings WHERE BookingId="+bookingId+";");
    }
    public ResultSet getHistory() throws SQLException{
//        statement.execute("SELECT * FROM Bookings WHERE bookedBy = '"+username+"' AND bookedForDate<date('now')");
        statement.execute("SELECT Bookings.*,Flights.travelTo,Flights.travelFrom,Flights.takeOffTime FROM Bookings " +
                "INNER JOIN Flights ON Bookings.flightId=Flights.flightId " +
                "WHERE Bookings.bookedBy = '"+username+"' AND bookedForDate<date('now') " +
                "ORDER BY bookedForDate");
        return statement.getResultSet();
    }
    static ArrayList<String> notifications = new ArrayList();
    void displaynotifications(){
        for(int i=0; i<=notifications.size()-1; i++){
            System.out.println("\n"+notifications.get(i)+"\n");
        }
    }
    void getNotifications() throws SQLException{
        String query = "SELECT notification FROM notifications WHERE username=" + "'"+username+"'";
        statement.execute(query);
        ResultSet rset = statement.getResultSet();
        if(rset.next()){
            while(rset.next()){
                notifications.add(rset.getString("notification"));
        }
        }
        else{notifications.add("No Updates!");}
        displaynotifications();

    }
}


/*
* 101,wash WC, new york,eseats, bseats,time
* 'wash DC'
*'wash' 'DC'
* */