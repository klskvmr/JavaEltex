package ru.eltex;

public class Task<T extends User> {
    private T owner;
    private String task;

    //private V qa;

    public Task(T owner) {
        this.owner = owner;
    }

    public T getOwner() {
        return this.owner;
    }

    public void setOwner(T owner) {
        this.owner = owner;
    }
}