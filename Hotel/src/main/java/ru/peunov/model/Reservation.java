package ru.peunov.model;

import org.hibernate.annotations.Cascade;
import ru.peunov.HibernateUtil;
import ru.peunov.dao.ReservationDAO;
import ru.peunov.dao.ResidentDAO;
import ru.peunov.enums.NumberClass;
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
    private int id;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.EAGER)
    private List<Resident> residents;

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
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

    public Reservation(){}

    public Reservation(List<Resident> residents, Calendar start, Calendar finish, NumberClass numberClass) {
        this.numberClass = numberClass;
        this.residents = residents;
        this.start = start;
        this.finish = finish;
    }

    public void updateResidents(){
        ResidentDAO residentDAO = new ResidentDAO(HibernateUtil.getSessionFactory());
        for(Resident resident : residents){
            residentDAO.create(resident);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
