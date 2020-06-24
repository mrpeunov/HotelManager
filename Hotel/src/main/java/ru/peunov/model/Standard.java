package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ST")
public class Standard extends Number {

    public Standard(int capacity, int price){
        this.capacity = capacity;
        this.price = price;
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.STANDARD;
    }

    public Standard(){
        hybridism = new ClassicalHybridism();
    }

}
