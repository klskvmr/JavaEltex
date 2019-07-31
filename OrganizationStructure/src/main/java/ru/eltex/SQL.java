package ru.eltex;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.sql.*;
import java.util.Arrays;

public class SQL {
    //    private static Statement statement;
    private Connection connection;

    {
        connection = null;
        try {
            connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD);
            //statement = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows(SQLException.class)
    public void getUnion() {
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT id, fio, number, email FROM developer UNION SELECT id, fio, number, email from manager ORDER BY id;");

        while (resultSet.next()) { // проход по полученным записям
            System.out.println("id = " + resultSet.getInt("id") + ", fio = " + resultSet.getString("fio")
                    + ", number = " + resultSet.getString("number") + ", email = " + resultSet.getString("email"));
        }
    }

    @SneakyThrows(SQLException.class)
    private void fillLanguagesTable() {
        @Cleanup Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO dev_lang(language) VALUE ('Java');");
        statement.executeUpdate("INSERT INTO dev_lang(language) VALUE ('C#');");
        statement.executeUpdate("INSERT INTO dev_lang(language) VALUE ('C');");
        statement.executeUpdate("INSERT INTO dev_lang(language) VALUE ('C++');");
        statement.executeUpdate("INSERT INTO dev_lang(language) VALUE ('Python');");
        statement.executeUpdate("INSERT INTO dev_lang(language) VALUE ('Erlang');");
    }

    @SneakyThrows(SQLException.class)
    public void getSecondViewDevelopers() {
        @Cleanup Statement statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS dev;");
        statement.execute("DROP TABLE IF EXISTS dev_id;");
        statement.execute("DROP TABLE IF EXISTS dev_lang;");

        statement.execute("CREATE TABLE dev(id INT NOT NULL AUTO_INCREMENT, fio VARCHAR(45), number VARCHAR(12), email VARCHAR(30), PRIMARY KEY(id));");
        statement.execute("CREATE TABLE dev_id (id INT NOT NULL AUTO_INCREMENT, id_dev INT(12), id_lang INT(12), PRIMARY KEY(id));");
        statement.execute("CREATE TABLE dev_lang (id INT NOT NULL AUTO_INCREMENT, language VARCHAR(20), PRIMARY KEY(id));");

        fillLanguagesTable();
        statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery("SELECT * FROM developer;"); // получение записей

        while (resultSet.next()) {
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO dev(fio, number, email)  VALUE ('" + resultSet.getString("fio") + "', '"
                    + resultSet.getString("number") + "', '" + resultSet.getString("email") + "');");

            String[] languages = resultSet.getString("languages").split(" ");

            for (String language : languages) {
                ResultSet resultSetLangId = statement.executeQuery("SELECT id FROM dev_lang WHERE language LIKE '%" + language + "%';"); // получение записей
                resultSetLangId.next();

                statement.executeUpdate("INSERT INTO dev_id(id_dev, id_lang)  VALUES ('" + resultSet.getInt("id") + "', '" + resultSetLangId.getInt("id") + "');");
            }
        }
    }

    @SneakyThrows(SQLException.class)
    private void fillSalesTable() {
        @Cleanup Statement statement = connection.createStatement();
        statement.executeUpdate("INSERT INTO  man_sale(item, cost) VALUES ('item1', 100);");
        statement.executeUpdate("INSERT INTO  man_sale(item, cost) VALUES ('item2', 200);");
        statement.executeUpdate("INSERT INTO  man_sale(item, cost) VALUES ('item3', 300);");
        statement.executeUpdate("INSERT INTO  man_sale(item, cost) VALUES ('item4', 400);");
        statement.executeUpdate("INSERT INTO  man_sale(item, cost) VALUES ('item5', 500);");
        statement.executeUpdate("INSERT INTO  man_sale(item, cost) VALUES ('item6', 600);");
    }

    @SneakyThrows(SQLException.class)
    public void getSecondViewManagers() {
        @Cleanup Statement statement = connection.createStatement();

        statement.execute("DROP TABLE IF EXISTS man;");
        statement.execute("DROP TABLE IF EXISTS man_id;");
        statement.execute("DROP TABLE IF EXISTS man_sale;");

        statement.execute("CREATE TABLE man (id INT NOT NULL AUTO_INCREMENT, fio VARCHAR(45), number VARCHAR(12), email VARCHAR(30), PRIMARY KEY(id));");
        statement.execute("CREATE TABLE man_id (id INT NOT NULL AUTO_INCREMENT, id_man INT(12), id_sale INT(12), PRIMARY KEY(id));");
        statement.execute("CREATE TABLE man_sale (id INT NOT NULL AUTO_INCREMENT, item VARCHAR(20), cost INT(10), PRIMARY KEY(id));");

        fillSalesTable();
        statement = connection.createStatement();


        ResultSet resultSet = statement.executeQuery("SELECT * FROM manager;"); // получение записей

        while (resultSet.next()) {
            statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO man(fio, number, email)  VALUE ('" + resultSet.getString("fio") + "', '"
                    + resultSet.getString("number") + "', '" + resultSet.getString("email") + "');");

            String[] sales = resultSet.getString("sales").split(",");

            for (String sale : sales) {
                String[] salesComponents = sale.split(" ");

                String[] items = salesComponents[0].split("@");
                for (String item : items) {

                    ResultSet resultSetSaleId = statement.executeQuery("SELECT id FROM man_sale WHERE item LIKE '%" + item + "%';"); // получение записей
                    resultSetSaleId.next();

                    statement.executeUpdate("INSERT INTO man_id(id_man, id_sale)  VALUES ('" + resultSet.getInt("id") + "', '" + resultSetSaleId.getInt("id") + "');");
                }
            }
        }
    }
}