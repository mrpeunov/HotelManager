package ru.peunov.logic;

public class Econom extends Number {
    public Econom(){
        hybridism = new ClassicalHybridism();
    }

    public boolean isFree(Reservation reservation) {
        return hybridism.isFree(reservation, allReservation);
    }
}
