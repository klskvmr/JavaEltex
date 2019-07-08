package ru.eltex;

public class Developer extends User {
    private String[] languaches;

    public Developer(String fio, String number, String email, String[] languaches) {
        super(fio, number, email);
        this.languaches = languaches;
    }

    public String[] getLanguaches() {
        return languaches;
    }

    public void setLanguaches(String[] languaches) {
        this.languaches = languaches;
    }
}
