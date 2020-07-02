package ru.peunov.model;

import ru.peunov.controller.MainController;

public class FinanceManager implements Manager {
    /**
     * Using Pattern Singleton
     */

    private static FinanceManager financeManager;

    private FinanceManager() {
        //Связь с базой данных
    };

    public static FinanceManager getInstance(){
        if(financeManager == null){
            financeManager = new FinanceManager();
        }
        return financeManager;
    }

    public void printAll(){
        System.out.println("Empty");
    }
}
