package ru.peunov.dao;

import org.hibernate.SessionFactory;
import org.hibernate.sql.Update;
import ru.peunov.model.Worker;

public class WorkerDAO extends DAO<Worker>{
    public WorkerDAO(SessionFactory factory){
        this.factory = factory;
    }

    Class<Worker> myGetClass() {
        return Worker.class;
    }

    @Override
    public void updateAll(Worker worker) {
        worker.updateSalary();
    }
}

