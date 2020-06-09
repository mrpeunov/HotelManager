import java.util.Calendar;
import java.util.GregorianCalendar;

public class HotelManager {
    public static void main(String[] args){
        Calendar start =  new GregorianCalendar(2020, Calendar.JUNE, 2);
        Reservation reservation = new Reservation(start, 5);
        System.out.println(reservation.getStart().getTime() + " " + reservation.getFinish().getTime());
    }
}
