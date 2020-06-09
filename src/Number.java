import java.util.List;

public class Number {
    //Номер гостиницы
    //
    private int serialNumber;
    private int maxPeople;
    private int price;
    List<Reservation> calendar;

    public Number(int serialNumber, int maxPeople, int price, List<Reservation> calendar) {
        this.serialNumber = serialNumber;
        this.maxPeople = maxPeople;
        this.price = price;
        this.calendar = calendar;
    }
}
