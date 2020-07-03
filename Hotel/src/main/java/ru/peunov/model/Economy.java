package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("EC")
public class Economy extends Number {
    public Economy(int capacity, int price){
        this.capacity = capacity;
        this.price = price;
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.ECONOMY;
        allReservation = new ArrayList<Reservation>();
    }

    public Economy(){
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.ECONOMY;
    }
}
