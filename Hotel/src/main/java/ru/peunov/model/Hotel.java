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
        ReservationDAO reservationDAO = new ReservationDAO(HibernateUtil.getSessionFactory());
        testData();
    }

    public void testData(){
        financeManager.printAll();
        reservationManager.printAll();
        numberManager.printAll();
        personalManager.printAll();
    }
}


