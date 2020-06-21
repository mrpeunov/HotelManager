package ru.peunov.logic;

import ru.peunov.enums.NumberClass;

public class Hostel extends Number {
    public Hostel(){
        hybridism = new HostelHybridism();
    }

    public Hostel(int id, int capacity, int price){
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        hybridism = new HostelHybridism();
        numberClass = NumberClass.HOSTEL;
    }
}
