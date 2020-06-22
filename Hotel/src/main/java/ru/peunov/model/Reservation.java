package ru.peunov.model;

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

    @OneToMany(mappedBy = "reservation")
    private List<Resident> residents;

    @ManyToOne
    @JoinColumn(name = "number_id")
    private Number number;

    @Column(name = "start")
    @Temporal(TemporalType.DATE)
    private Calendar start;

    @Column(name = "finish")
    @Temporal(TemporalType.DATE)
    private Calendar finish;

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
}
