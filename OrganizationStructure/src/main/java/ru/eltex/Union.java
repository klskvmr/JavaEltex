package ru.eltex;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.*;

public class Union {

    @SneakyThrows(SQLException.class)
    public static void getUnion() {
        @Cleanup Connection connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD); //соединение с БД
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT id, fio, number, email FROM developer UNION SELECT id, fio, number, email from manager ORDER BY id;");

        while (resultSet.next()) { // проход по полученным записям

            System.out.println("id = " + resultSet.getInt("id") + ", fio = " + resultSet.getString("fio")
                    + ", number = " + resultSet.getString("number") + ", email = " + resultSet.getString("email"));
        }
    }

    @SneakyThrows(SQLException.class)
    public static void fillLanguagesTable() {
        @Cleanup Connection connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD); //соединение с БД
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO dev(language) VALUE ('JAVA');");
        statement.executeUpdate("INSERT INTO dev(language) VALUE ('C#');");
        statement.executeUpdate("INSERT INTO dev(language) VALUE ('C');");
        statement.executeUpdate("INSERT INTO dev(language) VALUE ('C++');");
        statement.executeUpdate("INSERT INTO dev(language) VALUE ('Python');");
        statement.executeUpdate("INSERT INTO dev(language) VALUE ('Erlang');");
    }

    @SneakyThrows(SQLException.class)
    public static void getSecondView() {
        @Cleanup Connection connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD); //соединение с БД
        Statement statement = connection.createStatement();

        statement.execute("CREATE TABLE dev (id INT NOT NULL AUTO_INCREMENT, fio VARCHAR(45), number VARCHAR(12), email VARCHAR(30), PRIMARY KEY(id));");
        statement.execute("CREATE TABLE dev_id (id INT NOT NULL AUTO_INCREMENT, id_dev INT(12), id_lang INT(12), PRIMARY KEY(id));");
        statement.execute("CREATE TABLE dev_lang (id INT NOT NULL AUTO_INCREMENT, language VARCHAR(20), PRIMARY KEY(id));");


        ResultSet resultSet = statement.executeQuery("SELECT * FROM developer;"); // получение записей
        while (resultSet.next()) {
            statement.executeUpdate("INSERT INTO dev(fio, number, email)  VALUE ('" + resultSet.getString("fio") + "', '"
                    + resultSet.getString("number") + "', '" + resultSet.getString("email") + "');");
        }

        resultSet = statement.executeQuery("SELECT * FROM dev;"); // получение записей
        while (resultSet.next()) {
            statement.executeUpdate("INSERT INTO dev_id(id_dev) VALUE (" + resultSet.getInt("id") + ")");
        }
    }
}
