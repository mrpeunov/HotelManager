import java.util.List;

public class Hotel {
    //Таблица
    private String name;
    private String description;
    private String place;
    private int numbers;
    private int numbersCurrent;

    private List<Number> numberFund;

    public Hotel(String name, String description, String place) {
        this.name = name;
        this.description = description;
        this.place = place;
        this.numbers = 0;
        this.numbersCurrent = 0;

    }

    public void addNumber(Number newNumber){
        numberFund.add(newNumber);
        numbers++;
    }

    public void deleteNumber(Number deleteNumber){

    }



    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description){
        this.description = name;
    }

    public String getDescription() {
        return description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
