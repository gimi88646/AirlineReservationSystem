import java.util.ArrayList;

public class Admin extends Person {
    Admin(){super("Anonymous");}
    Admin(String username){
        super(username);
    }

    // implementation of methods that are displayed in driver class
    //this method takes to-from time.. number of passengers for a carrier as parameters
    public void addRoute(ArrayList<String> flightinfo){
        for (int i=0; i<=flightinfo.size(); i++){
            String flight = flightinfo.get(i);
            
        }
    }
    public void cancelRoute(){}
    //some more methods
    /*
    * flight ke database ke andar ek column he inactiveSince ka column hoga, jsme date us waqt aegi jab admin us particular flghtId ko inactive krdega
    * ab ye flight srf us din ki flights complete karegi, jis dino ki booking sme initialize ho chuki he
    * or koi naye din ki booking nahi legi
    * jese hii tamam flights complete karegii.. phr databse se dlete hojaegi
    * active till kaa column ...
    * select bookedForDate from bookings whereflightId  ORDER By bookedforDate DESC LIMIT 1
    * no more bookings on new date.. that is if any date has at least 1 booking it can take passengers
    *
    * js flight ko inactive kiya
    * */
}
