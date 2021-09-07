import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testInput {
    static Scanner input = new Scanner(System.in);
//    public enum Day { MON, TUE, WED, THU, FRI, SAT, SUN };




    public static void main(String[] args) throws ParseException {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR,-30);
        Date datePlus30 = cal.getTime();
        System.out.println(datePlus30);
        Pattern addressPattern = Pattern.compile("\\w+(\\s\\w+){2,}");
        while (true){
            System.out.print("enter address");
            String address = input.nextLine();
            Matcher matcher = addressPattern.matcher(address);
            System.out.println(matcher.matches());

            if(1==2) break;
        }
        System.out.println("""
                   
                   ********  Password guidelines  ********
                   Password must be at least 8 characters, no more than 20 characters
                   and must include at least one upper case letter, one lower case letter, and one numeric digit
                   
                    """);
        while (true) {

/*Password matching expression. Password must be at least 4 characters, no more than 8 characters,
and must include at least one upper case letter, one lower case letter, and one numeric digit*/

         break;
        }

        System.out.print(
                """
                        1. Add a route
                        2. Cancel a route
                        3. Cancel a flight
                        4. Cancel Booking
                        5. Log out
                        6. Exit
                        Please choose any of the above:\s"""
        );
        System.out.print(
                """
                        1. See Flights
                        2. View Notifications
                        3. View Bookings
                        4. Cancel Booking
                        5. View History
                        6. Log out
                        7. Exit
                        Please choose any of the above:\s"""
        );

        {
            System.out.println("hello");
            System.out.println("hello");
        }
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 1988);
//        cal.set(Calendar.MONTH, Calendar.JANUARY);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
//        Date dateRepresentation = cal.getTime();
//        System.out.println(dateRepresentation);
//        Calendar c= Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.DATE,27);
//        System.out.println(c);
//        int d = c.get(Calendar.DATE);
//        System.out.println(d);
//        Date datenow = new Date();
//        System.out.println(datenow);
//        String strdate = "2021-05-14";
//        Date dateObj = new SimpleDateFormat("yyyy-MM-dd").parse(strdate);
//        System.out.println(dateObj);
//        System.out.println(datenow.compareTo(dateObj));


        String date111 = "2021-08-09 01:26:30";
        String[] asd = date111.split(" ");
        System.out.println(asd[0]+asd[1]);
        System.out.println(String.format("%-20s","Date And Time")+"Notification");

        String time = Driver.inputTime();
        System.out.println(time);
        String abc ="";
        System.out.println(abc.equals(""));
//        int k = new Integer(12);
        testmethod(12,567);
        System.out.println("You do not have any active Bookings!".length());

        String cnic = inputCnic();
        System.out.println(cnic);
        String Name = inputName();
        System.out.println("name input by functon  = "+Name);
        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$"));
        String name;
        while(true){
            try {
                System.out.print("Full Name: ");
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
//        System.out.print();
        System.out.println("hekko");
        String A=  String.format("%-20s", 93); // prints: |93                  |
        String B=  String.format("%-16s", 94); // prints: |93                  |
        String C=  String.format("%-16s", 95); // prints: |93                  |
        System.out.print(A);
        System.out.print(B);
        System.out.println(C);
//        System.out.println("Flight ID\t\tFrom\t\tTo\t\tDeparture Time\t\tfullName\t\tCNIC\t\tClass");
        System.out.println("Flight ID\t\tFrom\t\tTo\t\tName\t\tCNIC\t\tClass\t\tDeparture Time\t\tDeparture Date\t\tBooking Date");

//        String name = "ghulam muhammad";
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
        System.out.println(capitalized);
        String query = "flightId="+101;
        query+= " OR flightId="+102;
        query+= " OR flightId="+103;
        query+= " OR flightId="+104;
        System.out.println("query = "+query);

        ArrayList<Integer> ints= new ArrayList<>();
        ints.add(1);
        ints.add(3);
        ints.add(2);
        int a = ints.get(1);
        System.out.println("the int is "+a);

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String ourformat = formatter.format(date.getTime());
        System.out.println(ourformat);

        User user=new User("gimi");
        user=null;
        System.out.println("user is null:" +user==null);
        date();
        Scanner inp = new Scanner(System.in);
//        String s = inp.nextLine();
//        String a = inp.next();
//        System.out.println(s);
//        System.out.println(a);
        Date date1 = new Date();
        System.out.print("Enter date: ");
        String datein = inp.next();
        SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = DateFor.format(datein);
        System.out.println( "date = "+datein);
        System.out.println("date after processing" + stringDate);
    }
    static void date(){
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
          }

    private static String inputName(){

        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z]+[\\-'\\s]?[a-zA-Z ]+$"));
        String name;
        while(true){
            try {
                System.out.print("Full Name: ");
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
                String cnicPatternStructure = "^[1-7][0-9]{4}-\\d{7}-\\d{1}$";
                Pattern cnicPattern = Pattern.compile(cnicPatternStructure);
                Matcher cnicPatternMatcher = cnicPattern.matcher(cnic);
                System.out.println("CNIC MATCHES = "+ cnicPatternMatcher.matches());
                if (!cnicPatternMatcher.matches()) throw new InputMismatchException();
                return cnic;
            } catch (InputMismatchException ex) {
                System.out.println("Please Enter a Valid CNIC number! ");
            }
        }
    }

    public static void testmethod (int... args){
        System.out.println(args[1]);
        System.out.println(args[0]);
    }

}
