package ru.peunov.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.peunov.HibernateUtil;
import ru.peunov.enums.NumberClass;
import ru.peunov.exception.NoReservationException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager implements Manager {
    /**
     * Using Pattern Singleton
     */
    private static ReservationManager reservationManager;
    private List<Reservation> reservations;

    private ReservationManager() {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        reservations = new ArrayList<Reservation>();
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Reservation> criteriaQuery = builder.createQuery(Reservation.class);
            Root<Reservation> root = criteriaQuery.from(Reservation.class);
            criteriaQuery.select(root);
            Query<Reservation> query = session.createQuery(criteriaQuery);
            reservations = query.getResultList();
            session.close();
        } catch (Exception e){  e.printStackTrace();  System.out.println("QQQQQQQQQQ");}
    };

    public static ReservationManager getInstance(){
        if(reservationManager == null){
            reservationManager = new ReservationManager();
        }
        return reservationManager;
    }

    public void printAll() {
        System.out.println("Empty!!!");
    }

    public void addReservation(Reservation reservation) throws NoReservationException {
        Boolean flag = false;
        NumberManager numberManager = NumberManager.getInstance();
        List<Number> numbers = numberManager.getNumbers();
        for(Number number : numbers){
            if(number.isFree(reservation)){
                number.addReservation(reservation);
                flag = true;
                break;
            }
        }
        if(!flag) throw new NoReservationException("Not found free number");
    };

    public List<Reservation> getReservationForNumber(long id){
        List<Reservation> reservationsForNumber = new ArrayList<Reservation>();
        for(Reservation reservation : reservations){
            if(id == reservation.getNumber().getId()){
                reservationsForNumber.add(reservation);
            }
        }
        return reservationsForNumber;
    }
}
