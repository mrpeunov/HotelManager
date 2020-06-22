package ru.peunov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        } catch (Exception e){ System.out.println(e.toString());}

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


}
