package ru.eltex.koloskova.sample;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpVersion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import org.codehaus.jackson.map.ObjectMapper;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Vertx vertx = Vertx.vertx(new VertxOptions().setWorkerPoolSize(40)); // количество обработчиков
        HttpClientOptions options = new HttpClientOptions().
                setProtocolVersion(HttpVersion.HTTP_2).
                setSsl(true).
                setUseAlpn(true).
                setTrustAll(true);
        HttpClient client = vertx.createHttpClient(options);

        client.requestAbs(HttpMethod.GET, "http://localhost:8083/",
                result -> {
                    result.bodyHandler(body -> {
                        try {
                            String s = body.toString();

                            ObjectMapper mapper = new ObjectMapper();
                            User[] users = mapper.readValue(s, User[].class);
                            mapper.writeValue(new File("users.json"), users);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }).end();

        Thread.sleep(3000);
        GridPane gridPane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));

        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.setTitle("ALL USERS");
        stage.setWidth(700);
        stage.setHeight(400);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}