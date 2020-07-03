package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import java.util.Calendar;
import java.util.List;

public class ClassicalHybridism implements Hybridism {
    public boolean isFree(Reservation reservation, Number number){
        int requiredSize = reservation.getResidents().size();
        int currentSize = number.getCapacity();
        if(requiredSize > currentSize){
            return false;
        } else {
            if(number.getNumberClass() == reservation.getNumberClass()){
                List<Reservation> allReservation = number.getAllReservation();
                if (allReservation.isEmpty()) { return true; }

                boolean bool = true;
                for(Reservation checkReservation : allReservation){
                    Calendar startReservation = checkReservation.getStart();
                    Calendar finishReservation = checkReservation.getFinish();
                    Calendar start = reservation.getStart();
                    Calendar finish = reservation.getFinish();

                    if(!(startReservation.after(finish) | finishReservation.before(start))) { bool = false; }
                }
                return bool;

            } else return false;
        }
    }

    public int getCost(Reservation reservation, Number number){
        Calendar start = reservation.getStart();
        Calendar finish = reservation.getFinish();
        long startTime = start.getTimeInMillis();
        long finishTime = finish.getTimeInMillis();
        long delta = finishTime - startTime;
        int days = (int)(delta / (24 * 60 * 60 * 1000));
        days += 1;
        int price = number.getPrice();
        return price * days;
    }
}
