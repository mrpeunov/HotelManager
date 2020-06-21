package ru.peunov.logic;

import ru.peunov.enums.NumberClass;

public class Economy extends Number {
    public Economy(int id, int capacity, int price){
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.ECONOMY;
    }
}
