package ru.peunov.model;

import org.hibernate.annotations.Cascade;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.ReservationDAO;
import ru.peunov.enums.NumberClass;
import ru.peunov.exception.NoReservationException;

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

    @OneToMany(mappedBy = "number", fetch = FetchType.EAGER)
    private List<Reservation> allReservation;

    public Number() {

    }

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
        return hybridism.isFree(reservation, this);
    }

    public NumberClass getNumberClass() {
        return numberClass;
    }

    public void setNumberClass(NumberClass numberClass) {
        this.numberClass = numberClass;
    }

    public void addReservation(Reservation reservation)  {
        reservation.setNumber(this);
        allReservation.add(reservation);
        ReservationDAO reservationDAO = new ReservationDAO(HibernateUtil.getSessionFactory());
        reservationDAO.create(reservation);
    }

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", capacity=" + capacity +
                ", price=" + price +
                ", reservation + " + allReservation.size() +
                '}';
    }

    public List<Reservation> getAllReservation() {
        return allReservation;
    }

    public void setAllReservation(List<Reservation> allReservation) {
        this.allReservation = allReservation;
    }

    public long getId() {
        return id;
    }


}
