import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testInput {
    static Scanner input = new Scanner(System.in);
    public enum Day { MON, TUE, WED, THU, FRI, SAT, SUN };


    public static void main(String[] args) {
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
                //4XXXX-XXXXXXX-X
//                String cnicPatternStructure = "4[0-9]{4}-[0-9]{7}-[0-9]";
//                String cnicPatternStructure = "^4\\d{4}-\\d{7}-\\d{1}$";
                String cnicPatternStructure = "^[1-7][0-9]{4}-\\d{7}-\\d{1}$";
                Pattern cnicPattern = Pattern.compile(cnicPatternStructure);
                Matcher cnicPatternMatcher = cnicPattern.matcher(cnic);
                System.out.println("CNIC MATCHES = "+ cnicPatternMatcher.matches());
                if (!cnicPatternMatcher.matches()) throw new InputMismatchException();
                break;
            } catch (InputMismatchException ex) {
                System.out.println("Please Enter a Valid CNIC number! ");
            }
        }
        return cnic;
    }

    public static void testmethod (int... args){
        System.out.println(args[1]);
        System.out.println(args[0]);
    }

}
