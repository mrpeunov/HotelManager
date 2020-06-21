package ru.peunov.model;

import ru.peunov.logic.Reservation;

import java.util.ArrayList;

interface Hybridism {
    /**
     * Implementation pattern Strategy
     */
    public boolean isFree(Reservation reservation, ArrayList<Reservation> allReservation);
}
