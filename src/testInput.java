import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class testInput {
    public static void main(String[] args) {
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

        String name = "ghulam muhammad";
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
}
