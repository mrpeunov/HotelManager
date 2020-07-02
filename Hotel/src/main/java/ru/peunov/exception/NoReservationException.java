package ru.peunov.exception;

public class NoReservationException extends Exception {
    private String error;

    public NoReservationException(String str){
        this.error = str;
    }
}
