package ru.peunov.dao;

import org.hibernate.SessionFactory;
import ru.peunov.model.Salary;


public class SalaryDAO extends DAO<Salary>{
    public SalaryDAO(SessionFactory factory){
        this.factory = factory;
    }

    Class<Salary> myGetClass() {
        return Salary.class;
    }

    public void updateAll(Salary salary) { }
}