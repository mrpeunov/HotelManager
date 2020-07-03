package ru.peunov.dao;

import org.hibernate.SessionFactory;
import ru.peunov.model.Finance;
import ru.peunov.model.Number;

public class FinanceDAO extends DAO<Finance>{
    public FinanceDAO(SessionFactory factory){
        this.factory = factory;
    }

    Class<Finance> myGetClass() {
        return Finance.class;
    }

    public void updateAll(Finance finance) { }
}
