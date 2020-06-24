package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.*;

@Entity
@DiscriminatorValue("EC")
public class Economy extends Number {
    public Economy(int capacity, int price){
        this.capacity = capacity;
        this.price = price;
        hybridism = new ClassicalHybridism();
        numberClass = NumberClass.ECONOMY;
    }

    public Economy(){
        hybridism = new ClassicalHybridism();
    }
}
