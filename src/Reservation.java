import java.util.Calendar;

public class Reservation {
    private int id;
    private int amountPeople;
    private String[] namePeople;
    private String phoneNumber;
    private Calendar start;
    private Calendar finish;

    public Reservation(int id, int amountPeople, String[] namePeople, String phoneNumber, Calendar start, Calendar finish) {
        this.id = id;
        this.amountPeople = amountPeople;
        this.namePeople = namePeople;
        this.phoneNumber = phoneNumber;
        this.start = start;
        this.finish = finish;
    }


    public Reservation(Calendar start, int day, int id, int amountPeople, String[] namePeople, String phoneNumber){
        this.id = id;
        this.amountPeople = amountPeople;
        this.namePeople = namePeople;
        this.phoneNumber = phoneNumber;
        this.start = start;
        this.finish = (Calendar) start.clone();
        this.finish.add(Calendar.DAY_OF_MONTH, day);
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getFinish() {
        return finish;
    }

    public void setFinish(Calendar finish) {
        this.finish = finish;
    }
}
