package ru.peunov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.peunov.HibernateUtil;
import ru.peunov.model.Worker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class DAO<Entity> {
    protected SessionFactory factory;
    public void create(Entity entity){
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            session.save(entity);
            this.updateAll(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){ System.out.println(e.toString());}

    }

    public void saveOrUpdate(Entity entity){
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            this.updateAll(entity);
            session.saveOrUpdate(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){System.out.println(e.toString());}

    }

    public abstract void updateAll(Entity entity);

    public Entity read(long key) {
        Entity result = null;
        try {
            Session session = factory.openSession();
            result = session.get(this.myGetClass(), key);
            session.close();
        } catch (Exception e){ System.out.println(e.toString());}
        return result;
    }

    abstract Class<Entity> myGetClass();

    public void update(Entity entity) {
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){ System.out.println(e.toString());}
    }

    public void delete(Entity entity) {
        try {
            Session session = factory.openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e){ System.out.println(e.toString());}
    }

    public List<Entity> getAll() throws NullPointerException{
        List<Entity> result = null;
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Entity> criteriaQuery = builder.createQuery(myGetClass());
            Root<Entity> root = criteriaQuery.from(myGetClass());
            criteriaQuery.select(root);
            Query<Entity> q = session.createQuery(criteriaQuery);
            result = q.getResultList();
            session.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
