package ru.peunov.enums;

public enum NumberClass {
    HOSTEL, ECONOMY, STANDARD, LUX;

    public static NumberClass parseNumberClass(String str){
        if(str.equals("Хостел")) return HOSTEL;
        if(str.equals("Эконом")) return ECONOMY;
        if(str.equals("Стандарт")) return STANDARD;
        return LUX;
    }
}
