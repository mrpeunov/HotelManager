package ru.peunov.model;

import ru.peunov.HibernateUtil;
import ru.peunov.dao.NumberDAO;
import ru.peunov.enums.NumberClass;

import java.util.ArrayList;

public class NumberManager {
    /**
     * Using Pattern Singleton
     */
    private static NumberManager numberManager;
    private ArrayList<Number> numbers = new ArrayList<Number>();

    private NumberManager() {
        //связь с базой данных
        //
        //
        //
        //
        // х
    };

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

    public ArrayList<Number> getNumbers() {
        return numbers;
    }
}
