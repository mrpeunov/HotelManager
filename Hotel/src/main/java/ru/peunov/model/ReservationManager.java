package ru.peunov.model;

public class ReservationManager {
    /**
     * Using Pattern Singleton
     */
    private static ReservationManager reservationManager;

    private ReservationManager() {};

    public static ReservationManager getInstance(){
        if(reservationManager == null){
            reservationManager = new ReservationManager();
        }
        return reservationManager;
    }
}
