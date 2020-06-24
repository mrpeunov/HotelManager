package ru.peunov.enums;

public enum Position {
    ADMINISTRATOR, MAID, SECURITY, PLUMBER;
    public static Position parsePosition(String string){
        Position result = null;
        if(string.equals("Администратор")){
            result = Position.ADMINISTRATOR;
        }
        if(string.equals("Горничная")){
            result = Position.MAID;
        }
        if(string.equals("Сантехник")){
            result = Position.PLUMBER;
        }
        if(string.equals("Охранник")){
            result = Position.SECURITY;
        }
        if(result == null){
            result = Position.ADMINISTRATOR;
        }
        return result;
    }
}