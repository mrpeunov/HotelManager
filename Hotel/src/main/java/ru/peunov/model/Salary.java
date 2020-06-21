package ru.peunov.model;

import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "salary")
public class SalaryModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name="worker_id")
    private WorkerModel worker;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Calendar date;
}
