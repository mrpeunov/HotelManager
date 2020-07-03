package ru.peunov.enums;

public enum Status {
    WORKED, DISMISSED;
    public static String getString(Status status){
        if(status == WORKED) return "Работает";
        else return "Уволен";
    }
}
