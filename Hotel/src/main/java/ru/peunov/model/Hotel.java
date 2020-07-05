package ru.peunov.model;

import ru.peunov.HibernateUtil;
import ru.peunov.dao.ReservationDAO;

public class Hotel {
    FinanceManager financeManager;
    NumberManager numberManager;
    PersonalManager personalManager;
    ReservationManager reservationManager;

    public Hotel(){
        financeManager = FinanceManager.getInstance();
        reservationManager = ReservationManager.getInstance();
        numberManager = NumberManager.getInstance();
        personalManager = PersonalManager.getInstance();
        //testData();
    }

    public static void updateAll(){
        NumberManager.update();
        PersonalManager.update();
        ReservationManager.update();
    }

    public void testData(){
        financeManager.printAll();
        reservationManager.printAll();
        numberManager.printAll();
        personalManager.printAll();
    }
}


