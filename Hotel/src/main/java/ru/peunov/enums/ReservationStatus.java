package ru.peunov.enums;

public enum ReservationStatus {
    CURRENT, CANCEL, PAID, CLOSE;
    public static String getString(ReservationStatus reservationStatus){
        if(reservationStatus == CURRENT) return "Забронировано";
        if(reservationStatus == CANCEL) return "Отмена";
        if(reservationStatus == PAID) return "Оплачено";
        return "Закрыто";
    }

    public static ReservationStatus getReservationStatus(String str) {
        if (str.equals("Забронировано")) return CURRENT;
        if (str.equals("Отмена")) return CANCEL;
        if (str.equals("Оплачено")) return PAID;
        return CLOSE;
    }
}