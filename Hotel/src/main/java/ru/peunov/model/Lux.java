package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@DiscriminatorValue("LX")
public class Lux extends Number {
    public Lux(int capacity, int price){
        this.capacity = capacity;
        this.price = price;
        this.allReservation = new ArrayList<Reservation>();
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.LUX;
    }

    public Lux(){
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.LUX;
    }


}
