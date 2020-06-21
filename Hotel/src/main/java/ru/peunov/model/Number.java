package ru.peunov.logic;

import ru.peunov.enums.NumberClass;

import java.util.ArrayList;

public abstract class Number {
    /*
    * Using pattern Strategy
    **/
    int id;
    int capacity;


    int price;
    NumberClass numberClass;
    Hybridism hybridism;
    ArrayList<Reservation> allReservation;

    public void addReservation(Reservation reservation) throws Exception {
        if(this.isFree(reservation)){
            allReservation.add(reservation);
        } else {
            throw new Exception("Number isn't free");
        }
    }

    public boolean isFree(Reservation reservation) {
        return hybridism.isFree(reservation, allReservation);
    }
    public NumberClass isClass(){ return numberClass; }



    public Number(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
