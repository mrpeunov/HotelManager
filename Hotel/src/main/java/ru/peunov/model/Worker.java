package ru.peunov.logic;

import ru.peunov.enums.Status;

import java.util.ArrayList;

public abstract class Worker {
    private String name;
    private int salary;
    private ArrayList<Integer> allSalary = new ArrayList<Integer>();
    private Status status;

    public void giveSalary(){
        allSalary.add(salary);
    };

    public void dismiss(){
        status = Status.DISMISSED;
    };

    public void giveAction(Action action) {};

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
