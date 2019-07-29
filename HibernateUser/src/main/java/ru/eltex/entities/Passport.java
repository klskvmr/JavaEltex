package ru.eltex;

import ru.eltex.entities.User;
import javax.persistence.*;

@Table(name = "passports")
public class Passport {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(mappedBy = "passport")
    private User user;
}
