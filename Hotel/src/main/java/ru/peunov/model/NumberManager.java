package ru.peunov.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.NumberDAO;
import ru.peunov.enums.NumberClass;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class NumberManager implements Manager {
    /**
     * Using Pattern Singleton
     */
    private static NumberManager numberManager;
    private List<Number> numbers;

    private NumberManager() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Number> criteriaQuery = builder.createQuery(Number.class);
            Root<Number> root = criteriaQuery.from(Number.class);
            criteriaQuery.select(root);
            Query<Number> q = session.createQuery(criteriaQuery);
            numbers = q.getResultList();
            for(Number number : numbers){
                number.setAllReservation(ReservationManager.getInstance().getReservationForNumber(number.getId()));
            }
            Collections.sort(numbers, new Comparator<Number>() {
                @Override
                public int compare(Number lhs, Number rhs) {
                    return lhs.getId() < rhs.getId() ? -1 : (lhs.getId() > rhs.getId()) ? 1 : 0;
                }
            });
            session.close();

        } catch (Exception e){
            System.out.println("Просто здравствуй, просто как дела");
            e.printStackTrace();
        }
    };

    public void printAll(){
        System.out.println("Номера");
        for(Number number : numbers){
            System.out.println(number.toString());
        }
        System.out.println("");
    }

    public static NumberManager getInstance(){
        if(numberManager == null){
            numberManager = new NumberManager();
        }
        return numberManager;
    }

    public void addNumber(int capacity, int price, NumberClass numberClass){
        Number newNumber;
        switch (numberClass){

            case HOSTEL:
                newNumber = new Hostel(price, capacity);
                break;

            case ECONOMY:
                newNumber = new Economy(price, capacity);
                break;

            case LUX:
                newNumber = new Lux(price, capacity);
                break;

            case STANDARD:
            default:
                newNumber = new Standard(price, capacity);
                break;
        }
        numbers.add(newNumber);
        NumberDAO numberDAO = new NumberDAO(HibernateUtil.getSessionFactory());
        numberDAO.create(newNumber);
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public static void update(){ numberManager = new NumberManager(); }

    public void updateNumber(long id, int capacity, int price, NumberClass numberClass){
        NumberDAO numberDAO = new NumberDAO(HibernateUtil.getSessionFactory());
        Number number = numberDAO.read(id);
        number.setPrice(price);
        number.setCapacity(capacity);
        number.setNumberClass(numberClass);
        numberDAO.saveOrUpdate(number);
        update();
    }

    public void deleteNumber(long id){
        NumberDAO numberDAO = new NumberDAO(HibernateUtil.getSessionFactory());
        numberDAO.delete(numberDAO.read(id));
        update();
    }
}
