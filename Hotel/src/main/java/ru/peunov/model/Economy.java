package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("EC")
public class Economy extends Number {
    public Economy(int capacity, int price, long id){
        this.capacity = capacity;
        this.price = price;
        this.id = id;
        this.allReservation = new ArrayList<Reservation>();
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.ECONOMY;

    }

    public Economy(){
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.ECONOMY;
    }
}
