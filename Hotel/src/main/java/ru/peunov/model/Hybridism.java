package ru.peunov.model;

import ru.peunov.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public interface Hybridism {
    /**
     * Implementation pattern Strategy
     */
    public boolean isFree(Reservation reservation, List<Reservation> allReservation);
}
