package ru.peunov.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "salary")
public class Salary {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "size")
    private int size;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    @JoinColumn(name="worker_id")
    private Worker worker;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    public Salary() {}

    public Salary(int size, Worker worker) {
        this.size = size;
        this.worker = worker;
        this.date = Calendar.getInstance();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
