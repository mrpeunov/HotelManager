package ru.peunov.enums;

public enum Position {
    ADMINISTRATOR, MAID, SECURITE, PLUMBER;
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
            result = Position.SECURITE;
        }
        if(result == null){
            result = Position.ADMINISTRATOR;
        }
        return result;
    }
}