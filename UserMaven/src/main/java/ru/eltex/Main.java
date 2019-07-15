package ru.eltex;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User(1, "Misha", "23481242", "ert@gmail");
        objectMapper.writeValue(new File("user.json"), user);
        String userJSON = objectMapper.writeValueAsString(user);

        User user1 = objectMapper.readValue(new File("user.json"), User.class);
    }
}
