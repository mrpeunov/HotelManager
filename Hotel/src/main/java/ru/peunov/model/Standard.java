package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ST")
public class Standard extends Number {

    public Standard(int id, int capacity, int price){
        //this.id = id;
        this.capacity = capacity;
        this.price = price;
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.STANDARD;
    }

    public Standard(){
        hybridism = new ClassicalHybridism();
    }

}
