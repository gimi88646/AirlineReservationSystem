import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import java.lang.WordUtils;

public class Driver {
    public static Scanner input = new Scanner(System.in);
    static Airline airline;

    public static void main(String[] args) {

        // see flights
        // login
        int choice;
        try {
            airline = new Airline();
            // airline.admin.addRoute();
        } catch (Exception throwables) {
            System.out.println("Something really went wrong");
        }
//        String c =inputCnic();

        do {
            try {

                if (airline.user.getSignedInStatus()) {
                    //how should i change the options if the user has performed sign in
                    //when user signs in and he is a regular user he should be able to see following options

                    System.out.print(
                            "1. See Flights\n" +
                            "2. View Bookings\n" +
                            "3. Cancel Booking\n" +
                            "4. View History\n" +
                            "5. Log out\n" +
                            "6. Exit\n" +
                            "Please choose any of the above: "
                    );
                    choice = input.nextInt();
                    if (choice > 6 || choice < 1)
                        throw new InputOutOfBound("Please make sure you made a right selection.");
                    switch (choice) {
                        case 1: {
                            // see flights
                            // after the flights are displayed there has to be option for booking
                            // that booking actually invokes airline.user.book()
                            ArrayList bookingInfo = new ArrayList();

                            String flight = seeFlights(bookingInfo);
                            if(flight.equals("goBack")){
                                break;
                            }else if (flight.equals("notFound")){
                                System.out.println("Sorry no Flights Available, Please choose another date. ");
                                break;
                            }
                            else  {
                                //start taking information.. iske liye method likhna chahiye kyuu user logedin me bhi yahii kam ho rha hoga
                                //static take info method should be implemented... which returns Arraylist of string[] each string array represents a passenger
                                ArrayList<String[]> passengers = takeInfoForPassengers((int)bookingInfo.get(1));
                                if(passengers==null){
                                    break;
                                }else {
                                    airline.user.book(passengers,bookingInfo);
                                    System.out.println("Booked Successfully! ");
                                }
                            }
                            //book ka method from user class
                            break;
                        }
                        case 2: {
                            //view Bookings
                            //the user should be able to see his bookings(this query will use username and date>=today )..
                            // this part should display the user of bookings of today and days forward
                            // for this the date of the system should be taken
                            // there should be query that compares the dates in sql with the current time and returns resultSet
                            // containing elements greater than or equals to the date of the system
                            ResultSet bookings = airline.user.getBookings();
                            displayBookings(bookings,false);
                            break;
                        }
                        case 3: {
                            // Cancel Booking
                            // this method should be implemented in user class, in user gets to to choose among his bookings and cancel that booking
                            // airline.user.cancelBooking() gets invoked
                            String bookingId = displayBookings(airline.user.getBookings(),true);
                            System.out.println("BookingId = "+bookingId);
                            airline.user.cancelBooking(bookingId);
                            break;
                        }
                        case 4: {
                            // view History
                            // the method should be in user class class.. and calling of method should be like airline.user.viewHistory
                            // the user gets to see the bookings...bookedBy "username" and date<now
                            // airline.user.viewHistory()
                            System.out.println("HISTORY");
                            displayBookings(airline.user.getHistory(),false);
                            break;
                        }
                        case 5: {
                            //logout ...airline class me
                            airline.user.logOut();
                            break;
                        }
                        case 6: {
                            System.exit(0);
                        }
                    }
                }
                else if (airline.admin.getSignedInStatus()) {
                    //if the user is an admin he should be able to following options
                    System.out.println(
                            "1. Add a route\n" +
                            "2. Cancel a route\n" +
                            "3. Cancel a flight\n" +
                            "4. Log Out\n" +
                            "5. Exit"
                    );
                    choice = input.nextInt();
                    if (choice > 5 || choice < 1) throw new InputOutOfBound("Make sure your input is correct");
                    switch (choice) {
                        case 1: {
                            // add a route
                            // the admin is supposed to enter a new flight and information relevant to the fight..
                            // and that information is inserted into the flights table in database
                            break;

                        }
                        case 2: {
                            //cancel a route
                            // the admin is supposed to cancel a route by making a selection amonn the flights available in flights table
                            break;
                        }
                        case 3: {
                            //cancel a flight
                            // the admin is supposed to enter a date and to-from , and on that specific date all the bookings of a to-from gets cancelled
                            break;
                        }
                        case 4: {
                            //log out
                            //when the admin logs out. the admin attribute in airline is assigned to null again
                            //airline.
                            airline.admin.logOut();
                            break;
                        }
                        case 5: {
                            System.out.println("Buh byeee");
                            System.exit(0);
                        }
                    }
                }
                //once the program starts the neither admin nor user is signed in.. and he gets to see following options
                //if !airline.user.isSigned ..if user is null that means it not yet signed in..
                else {
                    System.out.print(
                        "1. See flights\n" +
                        "2. Log in\n" +
                        "3. Sign up\n" +
                        "4. Exit\n\n" +
                        "Please choose any of the above: "
                    );
                    choice = input.nextInt();
                    if (choice > 4 || choice < 1) throw new InputOutOfBound("Make sure your input is correct");
                    switch (choice) {

                        // all the flight operations gets displayed
                        case 1: {
//                            methods me se string return hogi.. ya flightId hogi ya goBack ho..
//                            seeFlights();
                            //me chah rha hu ke user ko flightid selection ke sath sath go back ka bhi option du.. jo ussy le jaega program ke start me
//                            agar me ab user se input lu uski flight id ke according.. tou mujhe kese pata chale kese input sahi aya he
//                            or agar input sahi aya bhi he tou user ko peeche wale menu me jane ka option hona chahiye!
//                            agar fligts mili hi nahiii tou kyaa kare?
                            // yahan phr flight id puchengyy..
                            // uske baad phr arraylist banegi people ki...
//                            jitny travellers hongy utni baar information dali jaegi... har individual's info ki arraylist append hogi people arraylist me
//                            phr uske baad ke hoga.....
//                            me keh rha hu ke
//                                    airline.user.book(arraylist of people,);
                            break;
                        }

                        //login module
                        case 2: {
                            System.out.print("Username: ");
                            String username = input.next();
                            System.out.print("\nPassword: ");
                            String password = input.next();
                            airline.login(username, password);
                            break;
                        }
                        //sign up module
                        //Sign up information database me store hojaegi or wohii data user.login ke liye bhi use ki jaegiii
                        //jiska matlab hoga ke sign up hone ke baad wohii account sign in hoga
                        case 3: {
                            System.out.println("your Full Name: ");
                            String name = "";
                            name += input.nextLine().replace("\n", "");

//                            String cnic = input.next(); // a pattern should be declared
//                            System.out.println("Email Address: "); // a pattern should be declared to avoid false emails
//                            String email = input.next();
                            // date of birth
                            // phone number
                            // address
                            // select a username
                            // password
                            // once user sign up krleta he ... tou ussi data ki help se hum uska login karengy
                            // or program ko extra functionality ke ke sath continue karengy..
                            //airline.signUp();
                            break;
                        }
                        //End Program
                        case 4: {
                            System.out.println("\n\nBuh Byeee");
                            System.exit(0);
                        }
                    }
                }
            } catch (InputOutOfBound ex) {
                System.out.println(ex);
            } catch (InputMismatchException ex) {
                input.nextLine();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } while (true);
    }

    public  static String displayBookings(ResultSet resultSet,boolean wantsToCancelBooking) throws SQLException{
        // this method can print bookings that are history, as well as bookings that are not yet past
        // display boookingId .. bookedOndate bookedFordate cnic fullname seatType ...to From and time

        while(true){
            //headers
            System.out.println(
                    "_".repeat(147)+'\n'+
                    String.format("%-4s","")+
                            String.format("%-13s","Flight ID")+
                            String.format("%-15s","From")+
                            String.format("%-15s","To")+
                            String.format("%-20s","Name")+
                            String.format("%-19s","CNIC")+
                            String.format("%-9s","Class")+
                            String.format("%-18s","Departure Time")+
                            String.format("%-18s","Departure Date")+
                            String.format("%-16s","Booking Date")+"|\n"+
                            "=".repeat(147)+"|\n"+
                            String.format("%148s",'|')
            );
            int count=1;
            ArrayList<String> bookingIds = new ArrayList<>();
            while (resultSet.next()){

                bookingIds.add(resultSet.getString("bookingId"));
                //  148
                System.out.println(
                                String.format("%-4s",count+".")+
                                String.format("%-13s",resultSet.getString("flightId"))+
                                String.format("%-15s",resultSet.getString("travelFrom"))+
                                String.format("%-15s",resultSet.getString("travelTo"))+
                                String.format("%-20s",resultSet.getString("fullName"))+
                                String.format("%-19s",resultSet.getString("cnic"))+
                                String.format("%-9s",resultSet.getString("seatType"))+
                                String.format("%-18s",resultSet.getString("takeOffTime"))+
                                String.format("%-18s",resultSet.getString("bookedForDate"))+
                                String.format("%-16s",resultSet.getString("bookedOnDate"))+ "|"
                );
                count++;
            }
            if(count==1){
                System.out.println(String.format("%89s","You do not have any Bookings!")+
                        String.format("%59s",'|'));
            }
            System.out.println();

            if(wantsToCancelBooking){
                System.out.println(String.format("%-4s",count+".")+"Go Back");
                System.out.print("Choose a booking that you want to cancel: ");
                try{
                    int choice = input.nextInt();
                    if(choice>count|| choice<1) throw  new InputOutOfBound("Select From 1 to "+count);
                    if(choice==count){return "goBack";}
                    else return bookingIds.get(choice-1);
                }catch (InputMismatchException inputMismatchException){
                    System.out.println("Please Input Integers Only!");
                    input.nextLine();
                    continue;
                }catch (InputOutOfBound inputOutOfBound){
                    System.out.println(inputOutOfBound);
                    continue;
                }
            }
            return "";
        }

    }

    public static String getStrDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);  // 2021-12-12
        return strDate;
    }

    private static String inputName(){

        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$"));
        String name;
        while(true){
            try {
                System.out.print("Full Name: ");
                name = input.nextLine();
                name = input.nextLine();
                Matcher matcher = pattern.matcher(name);
                if(!matcher.matches()) throw new InputMismatchException();
                break;

            }
            catch (InputMismatchException e){
                System.out.println("Please enter a valid name!");
                input.nextLine();
            }
        }
        String capitalized="";
        boolean convertNext = true;
        for (char ch : name.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            capitalized+=ch;
        }
        return capitalized;
    }

    private static String inputCnic() {
        String cnic;
        while (true) {
            try {
                System.out.print("cnic should be separated by \"-\"\nCNIC Number: ");
                cnic = input.next();
                //4XXXX-XXXXXXX-X
//                String cnicPatternStructure = "4[0-9]{4}-[0-9]{7}-[0-9]";
                String cnicPatternStructure = "^[1-7][0-9]{4}-\\d{7}-\\d{1}$";

                Pattern cnicPattern = Pattern.compile(cnicPatternStructure);
                Matcher cnicPatternMatcher = cnicPattern.matcher(cnic);
                if (!cnicPatternMatcher.matches()) throw new InputMismatchException();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Please Enter a Valid CNIC number! ");
            }
        }
        return cnic;
    }

    private static String inputPhone() {
        String phone;
        while (true) {
            try {
                phone = input.next();
                String phonePatternStructure = "[\\d]{6,16}";
                Pattern cnicPattern = Pattern.compile(phonePatternStructure);
                Matcher cnicPatternMatcher = cnicPattern.matcher(phone);
                if (!cnicPatternMatcher.matches()) throw new InputMismatchException();
                break;
            } catch (InputMismatchException ex) {
                System.out.print("Please Enter a Valid CNIC number! ");
            }
        }
        return phone;
    }

    private static String showFlights(ResultSet resultSet) throws SQLException {
        boolean flightFound =false;
        System.out.println("\nAvailable Flights\n");
        //kyaa hi baat ho ke me sirf time puchoo ussy se or kahu in timings me se choose ke .. jissy wo mujhe index dega..
        ArrayList<String[]> flights = new ArrayList<>();
        while(resultSet.next()){
            flightFound=true;
            String[] flight = new String[2];
            flight[0]=resultSet.getString("flightId");
            flight[1]=resultSet.getString("takeOffTime");
            flights.add(flight);
        }
        if (flightFound){
            //sout(headers)
//            System.out.println("\tFlight ID\t\t|\tTime");
            System.out.println(String.format("%-4s","")+String.format("%-20s","Flight ID")+ String.format("%-20s","Time"));
            for(int i=0;i<flights.size();i++){
                //sout(info) of each flight
                String[] flight = flights.get(i);
                System.out.println(
                        String.format("%-4s",(i+1)+".")+
                        String.format("%-20s",flight[0])+
                        String.format("%-20s",flight[1])
                );
            }
            System.out.print(String.format("%-4s",(flights.size()+1))
                    +"Go back\n" +
                    "please make a choice between 1 and "+(flights.size()+1)+
                    "Choice: "
            );
            int choice = input.nextInt()-1;
            if (choice==flights.size()) {
                return "goBack";
            }
            else {
                return flights.get(choice)[0];
            }


        }


        else return "notFound";
        //yahan jaa kr see flights kaa pura hua
        // mujhe return karani chahiye string.. agar user peeche jana chahta he tou go back ki string jaegi.. or jahan se method call hua he wahan if go back then break ki instruction chale
    }

    private static String seeFlights(ArrayList bookingInfo) {

//        mujhe chahiye
//        flight id
//        seattype
//                number of passengers
//                date
        while (true) {
            try {

                System.out.print("Enter Date of Departure (Date format: yyyy-MM-dd): ");
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(input.next());
                //i have to show to-froms separately if the user has already selected a from .. i should exclude that origin from the options
//                ArrayList<String> origins = airline.getOperations(date,origin);
                ArrayList<String> froms = airline.getFroms(date);
                String from = inputFrom(froms);

                ArrayList<String> destinations = airline.getDestinations(date,from);
                String destination = inputDestination(destinations);

                System.out.print("How many passengers? ");
                int numberOfPessegers = input.nextInt(); // this number of passengers will be used for comparing with the count of booked flights in database
                System.out.print("Enter Type \n" +
                        "B for Business\n" +
                        "E for Economy\n" +
                        "Your Choice: ");
                char seatType = input.next().charAt(0);

                if (!(seatType == 'B' || seatType == 'E')) throw new InputMismatchException("Please choose between B and E");
                String flight = showFlights(airline.getFlights(date,destination,from,numberOfPessegers,seatType));
                // ye boolean ki jagah string ho.. jisme ya flight ids ho yaa goBack ho
                //phr yahii cheeez return karao jahan se method call hua he
                bookingInfo.add(getStrDate(date));
                bookingInfo.add(numberOfPessegers);
                bookingInfo.add(seatType);
                bookingInfo.add(flight);

                return flight;
            } catch (java.text.ParseException ex) {
                System.out.print("date input type mismatched\n" +
                        "Please re-enter correctly: ");
            } catch (InputMismatchException ex) {
                System.out.println("Please enter legal values!");
                input.nextLine();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (InputOutOfBound inputOutOfBound) {
                System.out.println(inputOutOfBound);
            }
        }
    }

    private static ArrayList<String[]> takeInfoForPassengers(int numberOfPassengers){
        ArrayList<String[]> passengers = new ArrayList<>();

        //full name and cnic
        for(int i=0;i<numberOfPassengers;i++){
            String[] passenger = new String[2];
            System.out.println("Information for passenger "+(i+1));
            passenger[0] = inputName();
            passenger[1] = inputCnic();
            passengers.add(passenger);

            if(i+1<numberOfPassengers){
                int choice;
                while (true){
                    try {
                        System.out.print(
                                "1. Continue for next passenger\n" +
                                        "2. drop\n" +
                                        "choice: ");
                        choice = input.nextInt();
                        if (!(choice==1 || choice==2)) throw new InputOutOfBound("");
                        break;
                    }

                    catch (InputMismatchException ex){
                        System.out.println("Please input integers only");
                        input.nextLine();
                    }
                    catch (InputOutOfBound ex){
                        System.out.println("Please choose between 1 and 2.");
                    }

                }
                if(choice==2) return null;
            }
        }
        return passengers;
    }

    public static String inputFrom(ArrayList<String> froms) throws InputOutOfBound,InputMismatchException{
        System.out.println("Please Select an Origin: ");
        int fromsLength = froms.size();
        for (int i=0;i<fromsLength;i++){
            System.out.println((i+1)+". "+froms.get(i));
        }
        System.out.print("please make a choice from 1 to "+fromsLength+"\n" +
                "Fly From: ");
        int choice = input.nextInt();
        if (choice<1 ||choice>fromsLength) throw new InputOutOfBound("Please make sure your input lies in range");
        return froms.get(choice-1);
    }

    public static String inputDestination(ArrayList<String> destinations) throws InputOutOfBound,InputMismatchException{
        System.out.println("Please Select a Destination ");
        int destinationsLength = destinations.size();
        for (int i=0;i<destinationsLength;i++){
            System.out.println((i+1)+". "+destinations.get(i));
        }
        System.out.print("please make a choice from 1 to "+destinationsLength+"\n" +
                "Fly To: ");
        int choice = input.nextInt();
        if (choice<1 ||choice>destinationsLength) throw new InputOutOfBound("Please make sure your input lies in range");
        return destinations.get(choice-1);
    }
}

