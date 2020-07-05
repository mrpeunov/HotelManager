package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HostelHybridism implements Hybridism {
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
                Calendar start = reservation.getStart();
                Calendar finish = reservation.getFinish();

                long startTime = start.getTimeInMillis();
                long finishTime = finish.getTimeInMillis();
                int days = (int)((finishTime - startTime) / (24 * 60 * 60 * 1000)) + 1;
                System.out.println(days + " дней");
                int[] arr = new int[days];
                for(int i = 0; i < days; i++) arr[i] = 0;

                for(Reservation checkReservation : allReservation){
                    Calendar startReservation = checkReservation.getStart();
                    Calendar finishReservation = checkReservation.getFinish();
                    finish.add(Calendar.HOUR, 1);
                    if(!(startReservation.after(finish) | finishReservation.before(start))) {
                        finish.add(Calendar.HOUR, -1);
                        long startReservationTime = startReservation.getTimeInMillis();
                        long finishReservationTime = finishReservation.getTimeInMillis();

                        int delta = (int)((finishReservationTime - startReservationTime) / (24 * 60 * 60 * 1000)) + 1;
                        int startDelta = (int)((startTime - startReservationTime) / (24 * 60 * 60 * 1000)) + 1;
                        int finishDelta = (int)((finishTime - finishReservationTime) / (24 * 60 * 60 * 1000)) + 1;

                        if(startDelta > 0) {
                            if(finishDelta > 0){
                                int currentDelta = (int)((finishReservationTime - startTime) / (24 * 60 * 60 * 1000));
                                for(int i = 0; i < currentDelta +1 ; i++){
                                    arr[i] += checkReservation.getResidents().size();
                                }
                            }
                            else{
                                for(int i = 0; i < days; i++){
                                    arr[i] += checkReservation.getResidents().size();
                                }
                            }
                        }
                        else {
                            if(finishDelta > 0){
                                int currentDelta = (int)((startReservationTime - startTime) / (24 * 60 * 60 * 1000));
                                for(int i = currentDelta; i < currentDelta+delta; i++){
                                    arr[i] += checkReservation.getResidents().size();
                                }
                            }
                            else{
                                int currentDelta = (int)((startReservationTime - startTime) / (24 * 60 * 60 * 1000));
                                for(int i = currentDelta; i < days; i++){
                                    arr[i] += checkReservation.getResidents().size();
                                }
                            }
                        }
                        for(int i = 0; i < days; i++) System.out.print(arr[i]);
                        System.out.println("");
                    }
                }
                for(int i = 0; i < days; i++){
                    if(arr[i] + requiredSize > currentSize) bool = false;
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
        int people = reservation.getResidents().size();
        return price * days * people;
    }
}
