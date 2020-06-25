package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.*;

@Entity
@DiscriminatorValue("LX")
public class Lux extends Number {
    public Lux(int capacity, int price){
        this.capacity = capacity;
        this.price = price;
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.LUX;
    }

    public Lux(){
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.LUX;
    }


}
