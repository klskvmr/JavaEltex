package ru.eltex.koloskova.sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import lombok.Cleanup;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

@NoArgsConstructor
public class Controller implements Initializable {
    @FXML
    private GridPane gridPane;

    @Override
    @SneakyThrows(IOException.class)
    public void initialize(URL location, ResourceBundle resources) {
        @Cleanup FileReader fr = new FileReader("users.json");
        @Cleanup Scanner readFile = new Scanner(fr);

        ObjectMapper mapper = new ObjectMapper();
        User[] users = mapper.readValue(readFile.nextLine(), User[].class);

        for (int i = 0; i < users.length; i++) {
            gridPane.addRow(i + 1);
            gridPane.add(new Text(" " + users[i].getId()), 0, i + 1);
            gridPane.add(new Text(users[i].getFio()), 1, i + 1);
            gridPane.add(new Text(users[i].getPhone()), 2, i + 1);
            gridPane.add(new Text(users[i].getEmail()), 3, i + 1);
        }
    }
}
