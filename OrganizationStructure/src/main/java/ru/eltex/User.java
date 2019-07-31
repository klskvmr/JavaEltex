package ru.eltex;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@People(mass = 50, age = 23, sex = 1)

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
public abstract class User implements CSV, JSON, Comparable<User> {
    @Id
    @GeneratedValue

    private Integer id;
    private String fio;
    private String number;
    private String email;


    public User(String fio, String number, String email) {
        this.fio = fio;
        this.number = number;
        this.email = email;
    }

    public String toCSV() {
        return this.id + " " + this.fio + ";" + this.number + ";" + this.email;
    }

    public void fromCVS(String stringFromCVS) {
        String[] stringsArray = stringFromCVS.split(";");

        id = Integer.parseInt(stringsArray[0]);
        fio = stringsArray[1];
        number = stringsArray[2];
        email = stringsArray[3];
    }

    @Override
    public int compareTo(User user) {
        return fio.compareTo(user.getFio());
    }
}