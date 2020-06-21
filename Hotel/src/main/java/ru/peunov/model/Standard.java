package ru.peunov.model;

import ru.peunov.enums.NumberClass;
import ru.peunov.logic.Bathroom;
import ru.peunov.logic.ClassicalHybridism;

public class Standart extends Number {
    Bathroom bathroom;

    public Standart(int id, int capacity, int price){
        //this.id = id;
        this.capacity = capacity;
        this.price = price;
        hybridism = new ClassicalHybridism();
        bathroom = new Bathroom();
        numberClass = NumberClass.STANDARD;
    }

}
