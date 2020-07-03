package ru.peunov.model;

import org.hibernate.annotations.Cascade;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.ReservationDAO;
import ru.peunov.dao.ResidentDAO;
import ru.peunov.enums.NumberClass;
import ru.peunov.enums.ReservationStatus;
import ru.peunov.model.Number;
import ru.peunov.model.Resident;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER)
    private List<Resident> residents;

    @ManyToOne(optional = true)
    @JoinColumn(name = "number_id")
    private Number number;

    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private NumberClass numberClass;

    @Column(name = "start")
    @Temporal(TemporalType.DATE)
    private Calendar start;

    @Column(name = "finish")
    @Temporal(TemporalType.DATE)
    private Calendar finish;

    @Column(name = "comment")
    private String comment;

    @Column(name = "status")
    @Enumerated(EnumType.ORDINAL)
    private ReservationStatus reservationStatus;

    public Reservation(){}

    public Reservation(List<Resident> residents, Calendar start, Calendar finish, NumberClass numberClass, String comment) {
        this.numberClass = numberClass;
        this.residents = residents;
        for(Resident resident : this.residents){
            resident.setReservation(this);
        }
        this.start = start;
        this.finish = finish;
        this.comment = comment;
        this.reservationStatus = ReservationStatus.CURRENT;
    }

    public void updateResidents(){
        ResidentDAO residentDAO = new ResidentDAO(HibernateUtil.getSessionFactory());
        for(Resident resident : residents){
            residentDAO.saveOrUpdate(resident);
        }
    }

    public NumberClass getNumberClass() {
        return numberClass;
    }

    public void setNumberClass(NumberClass numberClass) {
        this.numberClass = numberClass;
    }

    public List<Resident> getResidents() {
        return residents;
    }

    public void setResidents(List<Resident> residents) {
        this.residents = residents;
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getFinish() {
        return finish;
    }

    public void setFinish(Calendar finish) {
        this.finish = finish;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", residents=" + residents.size() +
                ", number=" + number +
                ", numberClass=" + numberClass +
                ", start=" + start.getTime() +
                ", finish=" + finish.getTime() +
                ", comment='" + comment + '\'' +
                ", reservationStatus=" + reservationStatus +
                '}';
    }
}
