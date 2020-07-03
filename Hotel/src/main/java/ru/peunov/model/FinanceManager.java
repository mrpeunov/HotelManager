package ru.peunov.model;

import ru.peunov.HibernateUtil;
import ru.peunov.controller.MainController;
import ru.peunov.dao.FinanceDAO;
import ru.peunov.dao.ReservationDAO;

import java.util.List;

public class FinanceManager implements Manager {
    /**
     * Using Pattern Singleton
     */

    private static FinanceManager financeManager;
    private List<Finance> finances;

    private FinanceManager() {
        FinanceDAO financeDAO = new FinanceDAO(HibernateUtil.getSessionFactory());
        finances = financeDAO.getAll();
    };

    public static FinanceManager getInstance(){
        if(financeManager == null){
            financeManager = new FinanceManager();
        }
        return financeManager;
    }

    public void printAll(){
        System.out.println("Финансы");
        for(Finance finance : finances){
            System.out.println(finance.toString());
        }
        System.out.println("");
    }

    public void addIncome(long id){
        ReservationDAO reservationDAO = new ReservationDAO(HibernateUtil.getSessionFactory());
        Reservation reservation = reservationDAO.read(id);
        Number number = reservation.getNumber();
        int cost = number.getCost(reservation);
        Finance finance = new Finance(true, cost);
        FinanceDAO financeDAO = new FinanceDAO(HibernateUtil.getSessionFactory());
        financeDAO.create(finance);
        update();
    }

    public void addConsumption(Salary salary){
        Finance finance = new Finance(false, salary.getSize());
        FinanceDAO financeDAO = new FinanceDAO(HibernateUtil.getSessionFactory());
        financeDAO.create(finance);
        update();
    }

    private void update(){
        financeManager = new FinanceManager();
    }

    public List<Finance> getFinances() {
        return finances;
    }

    public void setFinances(List<Finance> finances) {
        this.finances = finances;
    }
}
