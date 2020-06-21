package ru.peunov.model;

import ru.peunov.logic.Reservation;

import java.util.ArrayList;

public class HostelHybridism implements Hybridism {
    public boolean isFree(Reservation reservation, ArrayList<Reservation> allReservation){
        //необходима реализация
        return false;
    }
}
