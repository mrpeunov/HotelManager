package ru.peunov.model;

public class Hotel {
    FinanceManager financeManager;
    NumberManager numberManager;
    PersonalManager personalManager;
    ReservationManager reservationManager;

    public Hotel(){
        financeManager = FinanceManager.getInstance();
        numberManager = NumberManager.getInstance();
        personalManager = PersonalManager.getInstance();
        reservationManager = ReservationManager.getInstance();
    }
}


