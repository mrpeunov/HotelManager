package ru.peunov.enums;

public enum ReservationStatus {
    CURRENT, CANCEL, PAID, CLOSE;
    public static String getString(ReservationStatus reservationStatus){
        if(reservationStatus == CURRENT) return "Забронировано";
        if(reservationStatus == CANCEL) return "Отмена";
        if(reservationStatus == PAID) return "Оплачено";
        return "Закрыто";
    }
}
