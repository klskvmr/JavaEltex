package ru.eltex;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DUMP {
    @SneakyThrows(SQLException.class)
    protected static void developerToSQL(Developer developer) {
        Connection connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD); //соединение с БД
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO developer(fio, number, email, languages)  VALUE ('" + developer.getFio() + "', '"
                + developer.getNumber() + "', '" + developer.getEmail() + "', '" + developer.getLanguagesInString() + "');");

        connection.close(); // отключение от БД
    }

    @SneakyThrows(SQLException.class)
    protected static void managerToSQL(Manager manager) {
        @Cleanup Connection connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD); //соединение с БД
        Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO manager(fio, number, email, sales)  VALUE ('" + manager.getFio() + "', '"
                + manager.getNumber() + "', '" + manager.getEmail() + "', '" + manager.getSalesInString() + "');");

        connection.close(); // отключение от БД
    }

    @SneakyThrows(SQLException.class)
    public static ArrayList<Developer> developersFromSQL() {
        ArrayList<Developer> developers = new ArrayList<>();

        @Cleanup Connection connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD); //соединение с БД
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM developer;"); // получение записей

        while (resultSet.next()) { // проход по полученным записям
            Developer developer = new Developer();

            developer.setId(resultSet.getInt("id"));
            developer.setFio(resultSet.getString("fio"));
            developer.setNumber(resultSet.getString("number"));
            developer.setEmail(resultSet.getString("email"));

            if (resultSet.getString("languages") == null) {
                developer.setLanguages(new String[]{});
            } else {
                String[] languages = resultSet.getString("languages").split(" ");
                developer.setLanguages(languages);
            }

            developers.add(developer);
        }

        connection.close(); // отключение от БД
        return developers;
    }

    @SneakyThrows({SQLException.class,})
    public static ArrayList<Manager> managersFromSQL() throws TypeException {
        ArrayList<Manager> managers = new ArrayList<>();

        @Cleanup Connection connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD); //соединение с БД
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM manager;"); // получение записей

        while (resultSet.next()) { // проход по полученным записям
            Manager manager = new Manager();

            manager.setId(resultSet.getInt("id"));
            manager.setFio(resultSet.getString("fio"));
            manager.setNumber(resultSet.getString("number"));
            manager.setEmail(resultSet.getString("email"));

            if (resultSet.getString("sales") == null) {
                manager.setSales(new Sale[]{});
            }

            String[] stringsArray = resultSet.getString("sales").split(","); // строка - одна sale

            try {
                Sale[] salesArray = new Sale[stringsArray.length];
                int counter = 0;
                for (String sale : stringsArray) {
                    String[] saleComponentsArray = sale.split(" "); // массив из составляющих одной продажи
                    String[] itemsArray = saleComponentsArray[0].split("@"); // массив товаров

                    salesArray[counter] = new Sale(itemsArray, Double.parseDouble(saleComponentsArray[1]));

                    counter++;
                }

                manager.setSales(salesArray);
            } catch (NumberFormatException error) {
                System.out.println(error.getMessage());
                throw new TypeException("NOT MANAGER");
            }

            managers.add(manager);
        }

        connection.close(); // отключение от БД

        return managers;
    }

    @SneakyThrows(IOException.class)
    public static void developersFromJSON(String filename) {
        @Cleanup Scanner inputFile = new Scanner(new FileInputStream(filename));

        while (inputFile.hasNextLine()) {
            Developer developer = new Developer();
            developer.fromJSON(inputFile.nextLine());

            DUMP.developerToSQL(developer);
        }
    }

    @SneakyThrows(IOException.class)
    public static void managersFromJson(String filename) {
        @Cleanup Scanner inputFile = new Scanner(new FileInputStream(filename));

        while (inputFile.hasNextLine()) {
            Manager manager = new Manager();
            manager.fromJSON(inputFile.nextLine());

            DUMP.managerToSQL(manager);
        }

    }

    @SneakyThrows(IOException.class)
    public static void developersToJson(String filename) {
        @Cleanup FileWriter fw = new FileWriter(filename);
        ArrayList<Developer> developers = DUMP.developersFromSQL();

        for (Developer developer : developers) {
            fw.write(developer.toJSON(filename));
            fw.write("\n");
        }

        fw.flush();
    }


    @SneakyThrows({IOException.class, TypeException.class})
    public static void managersToJson(String filename) {
        @Cleanup FileWriter fw = new FileWriter(filename);

        ArrayList<Manager> managers = DUMP.managersFromSQL();

        for (Manager manager : managers) {
            fw.write(manager.toJSON(filename));
            fw.write("\n");
        }
        fw.flush();
    }
}