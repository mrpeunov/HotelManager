package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HostelHybridism implements Hybridism {
    public boolean isFree(Reservation reservation, Number number){
        System.out.println("Dont working Hostel Hybridism");
        return false;
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
        int people = reservation.getResidents().size();
        return price * days * people;
    }
}
