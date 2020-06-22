package ru.peunov.dao;

import org.hibernate.SessionFactory;
import ru.peunov.model.Resident;
import ru.peunov.model.Salary;

public class ResidentDAO extends DAO<Resident>{
    public ResidentDAO(SessionFactory factory){
        this.factory = factory;
    }

    Class<Resident> myGetClass() {
        return Resident.class;
    }
    public void updateAll(Resident resident) { }
}

