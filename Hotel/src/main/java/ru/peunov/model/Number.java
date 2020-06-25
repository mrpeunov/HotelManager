package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "number")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "NC")
public abstract class Number {
    /*
    * Using pattern Strategy
    **/

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private long id;

    @Column(name = "capacity")
    int capacity;

    @Column(name = "price")
    int price;

    @Transient
    NumberClass numberClass;

    @Transient
    Hybridism hybridism;

    @OneToMany(mappedBy = "number")
    private List<Reservation> allReservation;


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFree(Reservation reservation) {
        return hybridism.isFree(reservation, allReservation);
    }

    public NumberClass getNumberClass() {
        return numberClass;
    }


    public void addReservation(Reservation reservation) throws Exception {
        if(this.isFree(reservation)){
            allReservation.add(reservation);
        } else {
            throw new Exception("Number isn't free");
        }
    }

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", price=" + price +
                '}';
    }

    public long getId() {
        return id;
    }
}
