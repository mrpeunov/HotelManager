package ru.peunov.logic;

import ru.peunov.enums.NumberClass;

public class Lux extends Number {
    Bathroom bathroom;
    SmartHouse smartHouse;

    public Lux(int id, int capacity, int price){
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        hybridism = new ClassicalHybridism();
        bathroom = new Bathroom();
        smartHouse = new SmartHouse();
        numberClass = NumberClass.LUX;
    }
}
