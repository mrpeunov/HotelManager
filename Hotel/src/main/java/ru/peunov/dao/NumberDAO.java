package ru.peunov.dao;

import org.hibernate.SessionFactory;
import ru.peunov.model.Number;
import ru.peunov.model.Salary;

public class NumberDAO extends DAO<Number>{
    public NumberDAO(SessionFactory factory){
        this.factory = factory;
    }

    Class<Number> myGetClass() {
        return Number.class;
    }

    public void updateAll(Number number) { }
}
