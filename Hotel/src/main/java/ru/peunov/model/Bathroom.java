package ru.peunov.logic;

public class Bathroom {
    private boolean state;

    public Bathroom() {
        this.state = true;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
