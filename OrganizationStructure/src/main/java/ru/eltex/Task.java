package ru.eltex;

public class Task<T , V extends User> {
    private T owner;
    private V qa;

    public Task(T owner, V qa) {
        this.owner = owner;
        this.qa = qa;
    }

    public T getOwner() {
        return owner;
    }

    public V getQa(){
        return qa;
    }

    public void setOwner(T owner) {
        this.owner = owner;
    }

    public void setQa(V qa) {
        this.qa = qa;
    }
}