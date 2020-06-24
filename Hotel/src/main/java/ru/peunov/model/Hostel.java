package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("HOS")
public class Hostel extends Number {
    public Hostel(){

        hybridism = new HostelHybridism();
        numberClass = NumberClass.HOSTEL;
    }

    public Hostel(int capacity, int price){
        //this.id = id;
        this.capacity = capacity;
        this.price = price;
        hybridism = new HostelHybridism();
        numberClass = NumberClass.HOSTEL;
    }

}
