package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("ST")
public class Standard extends Number {
    public Standard(int capacity, int price, long id){
        this.capacity = capacity;
        this.price = price;
        this.allReservation = new ArrayList<Reservation>();
        this.id = id;
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.STANDARD;

    }

    public Standard(){
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.STANDARD;
    }

}
