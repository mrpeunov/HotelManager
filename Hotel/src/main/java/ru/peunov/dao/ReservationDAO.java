package ru.peunov.dao;

import org.hibernate.SessionFactory;
import ru.peunov.model.Reservation;
import ru.peunov.model.Salary;

public class ReservationDAO extends DAO<Reservation>{
    public ReservationDAO(SessionFactory factory){
        this.factory = factory;
    }

    Class<Reservation> myGetClass() {
        return Reservation.class;
    }
    public void updateAll(Reservation reservation) {
        reservation.updateResidents();
    }
}
