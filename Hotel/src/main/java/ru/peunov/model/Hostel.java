package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.ArrayList;


@Entity
@DiscriminatorValue("HOS")
public class Hostel extends Number {
    public Hostel(){
        hybridism = new HostelHybridism();
        numberClass = NumberClass.HOSTEL;
    }

    public Hostel(int capacity, int price){
        this.capacity = capacity;
        this.price = price;
        this.allReservation = new ArrayList<Reservation>();
        hybridism = new HostelHybridism();
        numberClass = NumberClass.HOSTEL;
    }

}
