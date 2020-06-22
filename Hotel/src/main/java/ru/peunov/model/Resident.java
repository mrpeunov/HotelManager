package ru.peunov.logic;

import ru.peunov.model.Number;

public class Resident {
    private long id;
    private String name;
    private String contact;
    private Number number;

    public Resident(){}

    public Resident(String name, String contact, Number number) {
        this.name = name;
        this.contact = contact;
        this.number = number;
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

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
