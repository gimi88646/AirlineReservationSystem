public class Admin extends Person {
    Admin(){super("Anonymous");}
    Admin(String username){
        super(username);
    }

    // implementation of methods that are displayed in driver class
    //this method takes to-from time.. number of passengers for a carrier as parameters
    public void addRoute(){}
    public void cancelRoute(){}
    //some more methods
}
