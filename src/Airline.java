import javax.xml.crypto.Data;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
     void signUp(String username, String password,String fullName,String cnic,String contact,String address,String email,String gender,String dateOfBirth){
          //in sign up we will receive information from parameters and that information will then be stored in sql..
          //or and with that username and password information we will also execute users login method from this method..

          try {
               statement.execute("CREATE TABLE IF NOT EXISTS Users(username TEXT UNIQUE,password TEXT,fullName TEXT,cnic TEXT,contact TEXT,address TEXT,email TEXT,gender TEXT,dateOfBirth TEXT,whenAccountCreated TEXT,accountType TEXT)");
               statement.execute("INSERT INTO Users VALUES(" +
                       username+","+password+","+fullName+","+cnic+","+contact+","+address+","+email+","+gender+","+dateOfBirth+"NOW()"+"regular"+
                       ")");
               login(username,password);
          } catch (SQLException throwables) {
               throwables.printStackTrace();
          }

     }

     ResultSet getFlights(Date date, String to, String from,int numberOfPassengers,char seatType) throws SQLException {
          DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String strDate = dateFormat.format(date);  // 2021-12-12
          statement.execute("SELECT flightId,numberOf"+seatType+"catSeats FROM Flights WHERE travelTo='"+to+"' AND travelFrom='"+from+"';"); // jab inactiveSince ka attribute add hoga tou mujhe iska date check rkhna parega k
          ResultSet flightsResultSet = statement.getResultSet();

//          while(flightsResultSet.next()){
//               // result set dusri iteration me count kaa bata rha he
//               // jiska mtlb he he hamarii loop ke andar query krne pr hamari flights ki query corrupt ho rhi he
//               System.out.println("column 1= "+flightsResultSet.getInt("flightId"));
//               System.out.println("column 2= "+flightsResultSet.getInt("numberOf"+seatType+"catSeats"));
//               int flightId = flightsResultSet.getInt("flightId");  // error.. no such column 'flightId'
//               int numberOfEorBseatTypes = flightsResultSet.getInt("numberOf"+seatType+"catSeats");
//               statement.execute("SELECT count(*) AS seatCount FROM Bookings WHERE flightId ='"+flightId+"' AND seatType='"+seatType+"' AND bookedForDate='"+strDate+"';");
//               int seatCount = statement.getResultSet().getInt("seatCount");
//               System.out.println("seatcount = " + seatCount);
//               if(seatCount+numberOfPassengers<=numberOfEorBseatTypes){
//                    flightIds.add(flightId);
//               }
//               System.out.println("in while loop");
//          }
          ArrayList<int[]> flightsdata = new ArrayList();
          while(flightsResultSet.next()){
               int[] flightdata = new int[2];
               flightdata[0] = flightsResultSet.getInt("flightId");  // error.. no such column 'flightId'
               flightdata[1] = flightsResultSet.getInt("numberOf"+seatType+"catSeats");
               flightsdata.add(flightdata);
          }
          ArrayList<Integer> flightIds = new ArrayList<>();
          for(int i=0;i<flightsdata.size();i++){
               int flightId = flightsdata.get(i)[0];
               int numberOfEorBseatTypes = flightsdata.get(i)[1];
               statement.execute("SELECT count(*) AS seatCount FROM Bookings WHERE flightId ='"+flightId+"' AND seatType='"+seatType+"' AND bookedForDate='"+strDate+"';");
               int seatCount = statement.getResultSet().getInt("seatCount");
               System.out.println("seatcount = " + seatCount);
               if(seatCount+numberOfPassengers<=numberOfEorBseatTypes){
                    flightIds.add(flightId);
               }
          }
          String query = "flightId="+flightIds.get(0);
          for (int i=1;i<flightIds.size();i++){
               query+=" OR flightId="+flightIds.get(i);
          }

          System.out.println("query = "+query);
          statement.execute("SELECT * FROM Flights WHERE "+query+";");
          return  statement.getResultSet();
     }

     ArrayList<String> getFroms(Date date) throws SQLException{
          ArrayList<String> froms = new ArrayList();
          statement.execute("SELECT DISTINCT travelFrom FROM FLights;");
          ResultSet resultSet = statement.getResultSet();
          while (resultSet.next()) {
               froms.add(resultSet.getString("travelFrom"));
          }
          return froms;
     }
     ArrayList<String> getDestinations(Date date,String from) throws SQLException{
          ArrayList<String> destinations = new ArrayList();
          statement.execute("SELECT DISTINCT travelTo FROM flights WHERE travelFrom='"+from+"';");
          ResultSet resultSet = statement.getResultSet();
          while (resultSet.next()) {
               destinations.add(resultSet.getString("travelTo"));
          }
          return destinations;
     }
}


/**
 * CREATE TABLE BOOKINGS IF NOT EXISTS(
 * bookingID INT NOT NULL,
 * flightId INT NOT NULL,
 * bookedOnDate datetime NOT NULL,
 * bookedForDate date NOT NULL,
 * bookedBy TEXT NOT NULL,
 * fullName TEXT NOT NULL,
 * cnic TEXT NOT NULL,
 * PRIMARY KEY(bookingID),
 * FOREIGN KEY(flightId) REFERENCES Flights(flightId)
 * );
  */
//SELECT * FROM BOOKINGS WHERE bookedForDate<'2021-08-22';
//SELECT count(*) FROM BOOKINGS WHERE bookedForDate>'2021-08-22 and flightID = 'flightID of a specified to-from';



//flight id  ki number of bookings us specific category ke liye count karo, or dekho ke number of passengers add krne por wo exceed tou nahi ho rhi...
//agar exceed nahi ho rhi tou wo flight show karwao ...