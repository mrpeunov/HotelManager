package ru.peunov.model;

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
    };

    public static NumberManager getInstance(){
        if(numberManager == null){
            numberManager = new NumberManager();
        }
        return numberManager;
    }

    public void addNewNumber(int capacity, int price, NumberClass numberClass){
        int count = numbers.size() + 1;
        this.addNumber(capacity, count, price, numberClass);
    }

    public void addNumber(int capacity, int count, int price, NumberClass numberClass){
        Number newNumber;
        switch (numberClass){

            case HOSTEL:
                newNumber = new Hostel(count, price, capacity);
                break;

            case ECONOMY:
                newNumber = new Economy(count, price, capacity);
                break;

            case LUX:
                newNumber = new Lux(count, price, capacity);
                break;

            case STANDARD:
            default:
                newNumber = new Standard(count, price, capacity);
                break;
        }
        numbers.add(newNumber);
    }
}
