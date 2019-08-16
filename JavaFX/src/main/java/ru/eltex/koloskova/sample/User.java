package ru.eltex.koloskova.sample;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString

@Getter
@Setter
public class User {
    private Integer id;
    private String fio;
    private String phone;
    private String email;
}
