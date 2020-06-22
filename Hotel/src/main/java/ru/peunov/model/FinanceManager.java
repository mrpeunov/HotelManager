package ru.peunov.model;

public class FinanceManager {
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
}
