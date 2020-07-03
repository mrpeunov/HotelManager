package ru.peunov.model;


import ru.peunov.HibernateUtil;
import ru.peunov.dao.ReservationDAO;
import ru.peunov.enums.ReservationStatus;
import ru.peunov.exception.NoReservationException;
import java.util.ArrayList;
import java.util.List;

public class ReservationManager implements Manager {
    /**
     * Using Pattern Singleton
     */
    private static ReservationManager reservationManager;
    private List<Reservation> reservations;

    private ReservationManager() {
        ReservationDAO reservationDAO = new ReservationDAO(HibernateUtil.getSessionFactory());
        reservations = reservationDAO.getAll();
    };

    public static ReservationManager getInstance(){
        if(reservationManager == null){
            reservationManager = new ReservationManager();
        }
        return reservationManager;
    }

    public void printAll() {
        System.out.println("Бронирования");
        for(Reservation reservation : reservations){
            System.out.println(reservation.toString());
        }
        System.out.println("");
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
        else update();
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public void deleteReservation(long id){
        ReservationDAO reservationDAO = new ReservationDAO(HibernateUtil.getSessionFactory());
        reservationDAO.delete(reservationDAO.read(id));
        update();
    }

    private void update(){
        reservationManager = new ReservationManager();
    }

    public void updateStatus(long id, String str){
        ReservationDAO reservationDAO = new ReservationDAO(HibernateUtil.getSessionFactory());
        Reservation reservation = reservationDAO.read(id);
        ReservationStatus reservationStatus = ReservationStatus.getReservationStatus(str);
        reservation.setReservationStatus(reservationStatus);
        reservationDAO.saveOrUpdate(reservation);
        FinanceManager financeManager = FinanceManager.getInstance();
        if(reservationStatus == ReservationStatus.PAID){
            financeManager.addIncome(id);
        }

        update();
    }

    public Reservation getReservation(long id){
        Reservation result = null;
        for(Reservation reservation : reservations){
            if(reservation.getId() == id){
                result = reservation;
            }
        }
        return result;
    }
}
