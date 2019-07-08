package ru.eltex;

public class User {
    private String fio;
    private String number;
    private String email;

    public User (String fio, String number, String email){
        this.fio = fio;
        this.number = number;
        this.email = email;
    }

    public String getFio() {
        return fio;
    }

    public String getNumber() {
        return number;
    }

    public String getEmail() {
        return email;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}