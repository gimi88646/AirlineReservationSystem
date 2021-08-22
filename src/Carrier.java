public class Carrier {
    private String from;
    private String to;
    private int time;
    private boolean[] business = new boolean[10];
    private boolean[] economy = new boolean[20];
    Carrier(String to, String from, int time){
        this.to =to;
        this.from = from;
        this.time = time;
    }
    void cancelSeat(){}

    int bookSeat(char type) {
        int seatNumber=0;
        if (type == 'B') {
            for (int i=0;i<business.length;i++){
                if (business[i]!=false){
                    business[i]=true;
                    seatNumber=i;
                    break;
                }
            }
        }
        else {
            for (int i=0;i<economy.length;i++){
                if (economy[i]!=false){
                    economy[i]=true;
                    seatNumber=i;
                    break;
                }
            }
        }
        return seatNumber;
    }
}

