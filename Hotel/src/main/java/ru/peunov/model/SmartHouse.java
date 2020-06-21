package ru.peunov.logic;

public class SmartHouse {
    private boolean state;

    public SmartHouse(){
        this.state = true;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
