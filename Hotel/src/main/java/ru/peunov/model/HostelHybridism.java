package ru.peunov.model;

import ru.peunov.enums.NumberClass;

import java.util.ArrayList;
import java.util.List;

public class HostelHybridism implements Hybridism {
    public boolean isFree(Reservation reservation, Number number){
        System.out.println("Dont working Hostel Hybridism");
        return false;
    }
}
