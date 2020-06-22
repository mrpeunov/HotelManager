package ru.peunov.model;

import ru.peunov.model.Reservation;

import java.util.ArrayList;

public interface Hybridism {
    /**
     * Implementation pattern Strategy
     */
    public boolean isFree(Reservation reservation, ArrayList<Reservation> allReservation);
}
