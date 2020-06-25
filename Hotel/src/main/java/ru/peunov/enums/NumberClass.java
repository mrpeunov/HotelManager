package ru.peunov.enums;

public enum NumberClass {
    HOSTEL, ECONOMY, STANDARD, LUX;

    public static NumberClass parseNumberClass(String str){
        if(str.equals("Хостел")) return HOSTEL;
        if(str.equals("Эконом")) return ECONOMY;
        if(str.equals("Стандарт")) return STANDARD;
        return LUX;
    }

    public static String getString(NumberClass numberClass){
        if(numberClass == HOSTEL) return "Хостел";
        if(numberClass == ECONOMY) return  "Эконом";
        if(numberClass == STANDARD) return  "Стандарт";
        return "Люкс";
    }
}
