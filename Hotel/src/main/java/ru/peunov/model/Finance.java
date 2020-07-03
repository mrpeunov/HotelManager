package ru.peunov.model;


import javax.persistence.*;
import java.util.Calendar;

@Entity
@Table(name = "finance")
public class Finance {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    @Column(name = "sign")
    private boolean sign;

    @Column(name = "size")
    private int size;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    public Finance(){}

    public Finance(Boolean sign, int size){
        this.sign = sign;
        this.size = size;
        date = Calendar.getInstance();
    }

    public boolean isSign() {
        return sign;
    }

    public void setSign(boolean sign) {
        this.sign = sign;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Finance{" +
                "id=" + id +
                ", sign=" + sign +
                ", size=" + size +
                ", date=" + date.getTime() +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
