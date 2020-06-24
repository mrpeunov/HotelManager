package ru.peunov.model;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.WorkerDAO;
import ru.peunov.enums.Position;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class PersonalManager {
    /*
    * Using pattern Singleton
    **/
    private static PersonalManager personalManager;
    private List<Worker> personal;

    private PersonalManager() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Worker> criteriaQuery = builder.createQuery(Worker.class);
            Root<Worker> root = criteriaQuery.from(Worker.class);
            criteriaQuery.select(root);
            Query<Worker> q = session.createQuery(criteriaQuery);
            personal = q.getResultList();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    };

    public static PersonalManager getInstance(){
        if(personalManager == null){
            personalManager = new PersonalManager();
        }
        return personalManager;
    }

    public void makeNewWorker(String name, int salary, Position position){
        Worker worker = new Worker(name, salary, position);
        WorkerDAO workerDAO = new WorkerDAO(HibernateUtil.getSessionFactory());
        workerDAO.create(worker);
        personal.add(worker);
    }
}
