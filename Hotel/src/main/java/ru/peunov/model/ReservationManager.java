package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import java.util.List;

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

    public boolean addReservation(Reservation reservation, NumberClass numberClass){
        Boolean flag = false;
        NumberManager numberManager = NumberManager.getInstance();
        List<Number> numbers = numberManager.getNumbers();
        for(Number number : numbers){
            if(number.isFree(reservation) && number.getNumberClass() == numberClass){
                try{
                    number.addReservation(reservation);
                    flag = true;
                    break;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return flag;
    };
}
