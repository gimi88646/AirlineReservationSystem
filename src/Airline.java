import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Airline {

//     Flight[] calender; // this will contain all the flights that are to takeoff within 30 days
     User user = new User();
     Admin admin= new Admin();

     Connection connection;
     Statement statement;
     Airline() throws SQLException{
          connection = DriverManager.getConnection("jdbc:sqlite:AirlineDatabase.db");
          statement = connection.createStatement();

          admin.setConnection(connection,statement);
          user.setConnection(connection,statement);

          //DATABASE TABLES creation
          {
               String createFlightsTableSql="CREATE TABLE Flight (" +
                       "flightId INTEGER," +
                       "travelTo TEXT," +
                       "travelFrom TEXT," +
                       "numberOfBcatSeats INTEGER," +
                       "numberOfEcatSeats INTEGER," +
                       "takeOffTime time," +
                       "activeTill date," +
                       "PRIMARY KEY(flightId)" +
                       ");";

               String createUsersTableSql ="CREATE TABLE IF NOT EXISTS Users (" +
                       "username TEXT UNIQUE," +
                       "password TEXT," +
                       "fullName TEXT," +
                       "cnic TEXT," +
                       "contact TEXT," +
                       "address TEXT," +
                       "email TEXT," +
                       "gender TEXT," +
                       "dateOfBirth date," +
                       "whenAccountCreated datetime," +
                       "userType TEXT," +
                       "PRIMARY KEY(username)" +
                       ");";
               String createBookingsTableSql = "CREATE TABLE BOOKINGS (" +
                       "bookingID INTEGER," +
                       "flightId INTEGER," +
                       "bookedOnDate date," +
                       "bookedForDate date," +
                       "bookedBy TEXT," +
                       "fullName TEXT," +
                       "cnic INTEGER," +
                       "seatType TEXT," +
                       "PRIMARY KEY(bookingID AUTOINCREMENT)," +
                       "FOREIGN KEY(flightId) REFERENCES Flights(flightId)" +
                       ");";

               //make sure the tables that the foreign key refers, exists before adding a foreign key
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
                       ");";

               statement.execute(createFlightsTableSql);
               statement.execute(createUsersTableSql);
               statement.execute(createCancelledFlightsTableSql);
               statement.execute(createNotificationTableSQL);
               statement.execute(createBookingsTableSql);

          }

          //check which active till is < dateNow and remove it
          statement.execute("DELETE FROM Flights WHERE activeTill<date('now')");


     }
     void login(String username, String password){
//          String query = "SELECT username,password WHERE username=''"
          try {
               statement.execute("CREATE TABLE IF NOT EXISTS Users(username TEXT UNIQUE,password TEXT,fullName TEXT,cnic TEXT,contact TEXT,address TEXT,email TEXT,gender TEXT,dateOfBirth TEXT,whenAccountCreated TEXT,userType TEXT)");
               statement.execute("SELECT username,userType,fullName FROM Users WHERE username='" + username + "' AND password='"+password+"'");
               ResultSet resultSet = statement.getResultSet();
               if (resultSet.next()){
                   //call users sign method.. in that sign in take the attributes from database and reassign the user object
                    // according to the in database
//                    if userType == 'regular'
                    String userType = resultSet.getString("userType");
                    if (userType.equals("regular")){
                         user.login(resultSet.getString("username"));
                         System.out.println("WELCOME "+resultSet.getString("fullName"));
                    }
                    else if(userType.equals("admin")){
                         admin.login(resultSet.getString("username"));
                         System.out.println("WELCOME "+resultSet.getString("fullName"));
                    }
               }else {
                    System.out.println("User Not Found, make sure you've entered right ID and password");
               }

          } catch (SQLException queryFailed) {
               System.out.println("Something went wrong: "+ queryFailed.getMessage());
          }
     }

     void signUp(ArrayList<String> userInfo) throws  SQLException{
          //in sign up we will receive information from parameters and that information will then be stored in sql..
          //or and with that username and password information we will also execute users login method from this method..
          StringBuilder insertInfoSql = new StringBuilder("INSERT INTO Users(fullName,username,password,cnic,contact,address,) VALUES('");


          for(int i=0;i<userInfo.size()-1;i++){
               insertInfoSql.append(userInfo.get(i)).append("','");
          }
          insertInfoSql.append(userInfo.get(userInfo.size()-1)).append("',datetime('now'),'regular');");
          statement.execute(insertInfoSql.toString());
//
//          last dob
//          then datetime now
//          accountType regular
//
//               statement.execute("INSERT INTO Users VALUES('" +
//                       username+"','"+password+"','"+fullName+"','"+cnic+"','"+contact+"','"+address+"','"+email+"','"+gender+"','"+dateOfBirth+"',datetime('now')"+",'regular'"+
//                       ")");

               login(userInfo.get(0),userInfo.get(1));
     }

     ResultSet getFlights(Date date, String to, String from,int numberOfPassengers,char seatType) throws SQLException {
          /*
          * dont include flights in the resultset if they are in cancelledFlights Table for the date user has provided
          * */

          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          // jo jo flighs to from match krta ho... date<=activeFill ...
          // uss flightId ke liye ye wali date cancellation table me nahiii honi chahiye

          String strDate = dateFormat.format(date);
          String sql ="SELECT * From (SELECT * " +
                  "                  FROM Flights " +
                  "                  WHERE  NOT (flightId IN " +
                  "                  (SELECT flightId " +
                  "                  FROM CancelledFlights " +
                  "                  WHERE cancelledDate = '"+strDate+"'))) " +
                  "  WHERE (( activeTill>='"+strDate+"' OR activeTill IS NUll)) " +
                  "AND travelFrom='"+from+"' AND travelTo='"+to+"';";
          statement.execute(sql); // jab inactiveSince ka attribute add hoga tou mujhe iska date check rkhna parega k
          ResultSet flightsResultSet = statement.getResultSet();

          ArrayList<int[]> flightsData = new ArrayList();
          while(flightsResultSet.next()){
               int[] flightdata = new int[2];
               flightdata[0] = flightsResultSet.getInt("flightId");  // error.. no such column 'flightId'
               flightdata[1] = flightsResultSet.getInt("numberOf"+seatType+"catSeats");
               flightsData.add(flightdata);
          }
          if(flightsData.size()==0) return null;
          //this is the case when resultset has some data....   flightsDate.size()==0 means no data
          ArrayList<Integer> flightIds = new ArrayList<>();
          for (int[] flightData : flightsData) {
               int flightId = flightData[0];
               int numberOfEorBseatTypes = flightData[1];
               statement.execute("SELECT count(*) AS seatCount FROM Bookings WHERE flightId ='" + flightId + "' AND seatType='" + seatType + "' AND bookedForDate='" + strDate + "';");
               int seatCount = statement.getResultSet().getInt("seatCount");
               if (seatCount + numberOfPassengers <= numberOfEorBseatTypes) {
                    flightIds.add(flightId);
               }
          }
          StringBuilder query = new StringBuilder("flightId=" + flightIds.get(0));
          for (int i=1;i<flightIds.size();i++){
               query.append(" OR flightId=").append(flightIds.get(i));
          }

          statement.execute("SELECT * FROM Flights WHERE "+query+";");
          return  statement.getResultSet();
          //case: no flights exist? if count inside while loop doesn't get incremented return null.. then handle it in driver
     }

     ResultSet getFlights(String from,String to) throws SQLException{
          //this method gets called by admin for route cancellation

          // this method is for admin where he gets to see the flights that are not scheduled to be deleted.
          statement.execute("SELECT flightId,travelTo,travelFrom,takeOffTime FROM Flights WHERE travelFrom='"+from+"' AND travelTo='"+to+"' AND ActiveTill IS NULL;");
          return statement.getResultSet();
     }
     ResultSet getFlights(String from,String destination, String date) throws  SQLException{
          /*
          @ this method gets flights when admin wants to cancel a flight for a date
          * flight id should not be present in the cancellation for the given date
           */
          String sql = "SELECT * From (SELECT * " +
                  "FROM Flights " +
                  "WHERE  NOT (flightId IN " +
                  "(SELECT flightId " +
                  "FROM CancelledFlights " +
                  "WHERE cancelledDate = '"+date+"'))) WHERE ( activeTill>='"+date+"' OR activeTill IS NUll) AND travelFrom='"+from+"' AND travelTo='"+destination+"';";
          statement.execute(sql);
          // got the ids and make sure the ids not already exists in cancellation table
          //get only those flightIds that are not present in cancellation table for the date provided
          return statement.getResultSet();
     }
     ArrayList<String> getFroms() throws SQLException{
          ArrayList<String> froms = new ArrayList();
          statement.execute("SELECT DISTINCT travelFrom FROM FLights;");
          ResultSet resultSet = statement.getResultSet();
          while (resultSet.next()) {
               froms.add(resultSet.getString("travelFrom"));
          }
          return froms;
     }
     ArrayList<String> getDestinations(String from) throws SQLException{
          ArrayList<String> destinations = new ArrayList();
          statement.execute("SELECT DISTINCT travelTo FROM flights WHERE travelFrom='"+from+"';");
          ResultSet resultSet = statement.getResultSet();
          while (resultSet.next()) {
               destinations.add(resultSet.getString("travelTo"));
          }
          return destinations;
     }
     boolean isUsernameAvailable(String username) throws SQLException{
          /**
           * method returns true if username is not present in the database table
           */
          statement.execute("SELECT username FROM Users WHERE username='"+username+"');");
          ResultSet resultSet = statement.getResultSet();
          return !resultSet.next();
     }
}


