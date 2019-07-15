package ru.eltex;

public abstract class User implements CSV, JSON {
    private Integer id;
    private String fio;
    private String number;
    private String email;

    public User(Integer id, String fio, String number, String email) {
        this.id = id;
        this.fio = fio;
        this.number = number;
        this.email = email;
    }

    public User() {
    }

    public Integer getId() {
        return id;
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

    public void setId(Integer id) {
        this.id = id;
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

    public String toCSV() {
        return this.id + " " + this.fio + ";" + this.number + ";" + this.email;
    }

    public void fromCVS(String stringFromCVS) {
        String[] stringsArray = stringFromCVS.split(";");

        this.id = Integer.parseInt(stringsArray[0]);
        this.fio = stringsArray[1];
        this.number = stringsArray[2];
        this.email = stringsArray[3];
    }
}