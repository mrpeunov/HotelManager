package ru.peunov.model;

import javax.persistence.*;

@Entity
@Table(name = "resident")
public class Resident {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Resident(){}

    public Resident(String name, String contact, Number number) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
