package ru.peunov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.sql.Update;
import ru.peunov.HibernateUtil;
import ru.peunov.model.Worker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

