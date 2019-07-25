package ru.eltex;

import lombok.Cleanup;
import lombok.SneakyThrows;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class OrganizationStructure {
    public static void main(String[] args) {

//        Manager manager = new Manager();
//        Class cl = manager.getClass();
//        People pl = (People) cl.getAnnotation(People.class);


        removeAndCreateTables();

        ArrayList<Developer> developers = new ArrayList<>();
        ArrayList<Manager> managers = new ArrayList<>();

//        developers = getArrayListOfDevelopers();
//        managers = getArrayListOfManagers();


        DUMP.developersFromCSV("developers.csv");
        DUMP.managersFromCSV("managers.csv");

//        writeDevelopersToJSON("developersToSql.json", developers);
//        writeManagersToJSON("managersToSql.json", managers);
//
//        DUMP.developersFromJSON("developersToSql.json");
//        DUMP.managersFromJson("managersToSql.json");
//
//        DUMP.developersToJson("developers.json");
//        DUMP.managersToJson("managers.json");

//        Union.getUnion();

        //writeDevelopersToJSON(developers);
        //readDevelopersFromJSON(developers);
        //writeDevelopersCSV(developers);
        //readDevelopersFromCSV(developers); System.out.println();
        //writeManagersToJSON(managers);
        //readManagersFromJSON(managers);
        //writeManagersToCSV(managers);
        //readManagersFromCSV(managers);

        //printDevelopers(developers);
        //printManagers(managers);
    }

    @SneakyThrows(SQLException.class)
    public static void removeAndCreateTables() {
        @Cleanup Connection connection = DriverManager.getConnection(Authorization.HOST, Authorization.LOGIN, Authorization.PASSWORD); //соединение с БД
        Statement statement = connection.createStatement();
        statement.execute("DROP TABLE IF EXISTS developer;");
        statement.execute("DROP TABLE IF EXISTS manager;");
        statement.execute("CREATE TABLE developer (id INT NOT NULL AUTO_INCREMENT, fio VARCHAR(45), number VARCHAR(12), email VARCHAR(30),  languages VARCHAR(45), PRIMARY KEY(id));");
        statement.execute("CREATE TABLE manager (id INT NOT NULL AUTO_INCREMENT, fio VARCHAR(45), number VARCHAR(12), email VARCHAR(30),  sales VARCHAR(50),  PRIMARY KEY(id));");
    }

    private static void checkCompare() {
        Manager manager = new Manager(1, "Alex", "911", "apple@gmail.com", new Sale[]{new Sale(new String[]{"iphone", "ipad", "mac"}, 377771.1)});
        Manager manager1 = new Manager(1, "Jobs", "911", "apple@gmail.com", new Sale[]{new Sale(new String[]{"iphone", "ipad", "mac"}, 377771.1)});

        Developer developer = new Developer(1, "Jobs", "911", "apple@gmail.com", new String[]{"Java"});

        translateCompare(manager1.compareTo(developer));
    }

    private static void translateCompare(int number) {
        if (number == 0) {
            System.out.println("Users are equal");
        } else if (number > 0) {
            System.out.println("User 1 > User 2");
        } else {
            System.out.println("User 1 < User 2");
        }
    }

    private static ArrayList<Developer> getArrayListOfDevelopers() {
        ArrayList<Developer> developers = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            developers.add(new Developer(i, "Elon Mask" + i, "90" + i, i + "@gmail.com", new String[]{"Java", "C#"}));
        }
        return developers;
    }

    private static ArrayList<Manager> getArrayListOfManagers() {
        ArrayList<Manager> managers = new ArrayList<Manager>();

        for (int i = 0; i < 10; i++) {
            managers.add(new Manager(i, "Victor Pelevin" + i, "97" + i, i + "@gmail.com", new Sale[]{
                    new Sale(new String[]{"item1", "item2", "item3"}, 600.5 + i), new Sale(new String[]{"item4", "item5", "item6"}, 700.5 + i)}));
        }
        return managers;
    }

    @SneakyThrows(IOException.class)
    private static void writeDevelopersToCSV(ArrayList<Developer> developers) {
        @Cleanup FileWriter fw = new FileWriter("developers.csv");

        for (Developer developer : developers) {
            fw.write(developer.toCSV());
        }

        fw.flush();
    }

    @SneakyThrows(IOException.class)
    private static void writeManagersToCSV(ArrayList<Manager> managers) {
        @Cleanup FileWriter fw = new FileWriter("managers.csv");

        for (Manager manager : managers) {
            fw.write(manager.toCSV());
        }
        fw.flush();
    }

    @SneakyThrows(IOException.class)
    private static void writeDevelopersToJSON(String filename, ArrayList<Developer> developers) {
        @Cleanup FileWriter fw = new FileWriter(filename);
        for (Developer developer : developers) {
            fw.write(developer.toJSON(filename));
            fw.write("\n");
        }
        fw.flush();
    }

    @SneakyThrows(IOException.class)
    private static void writeManagersToJSON(String filename, ArrayList<Manager> managers) {
        @Cleanup FileWriter fw = new FileWriter(filename);
        for (Manager manager : managers) {
            fw.write(manager.toJSON(filename));
            fw.write("\n");
        }
        fw.flush();
    }

    @SneakyThrows(IOException.class)
    private static ArrayList<Developer> readDevelopersFromCSV(ArrayList<Developer> developers) {
        developers.clear();

        @Cleanup FileReader fr = new FileReader("developers.csv");
        @Cleanup Scanner readFile = new Scanner(fr);

        while (readFile.hasNextLine()) {
            Developer developer = new Developer();
            developer.fromCSV(readFile.nextLine());

            developers.add(developer);
        }

        return developers;
    }

    @SneakyThrows(IOException.class)
    private static ArrayList<Manager> readManagersFromCSV(ArrayList<Manager> managers) throws TypeException {
        managers.clear();

        @Cleanup Scanner readFile = new Scanner(new FileInputStream("managers.csv"));
        while (readFile.hasNextLine()) {
            Manager manager = new Manager();
            manager.fromCSV(readFile.nextLine());
            managers.add(manager);
        }

        return managers;
    }

    @SneakyThrows(IOException.class)
    public static ArrayList<Developer> readDevelopersFromJSON(ArrayList<Developer> developers) {
        developers.clear();

        @Cleanup Scanner inputFile = new Scanner(new FileInputStream("developers.json"));
        while (inputFile.hasNextLine()) {
            Developer developer = new Developer();
            developer.fromJSON(inputFile.nextLine());

            developers.add(developer);
        }

        return developers;
    }

    @SneakyThrows(IOException.class)
    public static ArrayList<Manager> readManagersFromJSON(ArrayList<Manager> managers) {
        managers.clear();

        @Cleanup Scanner inputFile = new Scanner(new FileInputStream("managers.json"));
        while (inputFile.hasNextLine()) {
            Manager manager = new Manager();
            manager.fromJSON(inputFile.nextLine());

            managers.add(manager);
        }

        return managers;
    }

    private static String getLanguagesInString(String[] languages) {
        String string = "";

        for (String language : languages) {
            string += language + " ";
        }
        return string;
    }

    private static void printDevelopers(ArrayList<Developer> developers) {
        for (Developer developer : developers) {
            System.out.println(developer.getId() + " " + developer.getFio() + " " + developer.getNumber() + " " + developer.getEmail() + " " + getLanguagesInString(developer.getLanguages()));
        }
    }

    private static String getSalesInString(Sale[] sales) {
        String string = "";

        for (int i = 0; i < sales.length; i++) {
            String[] items = sales[i].getItems();

            for (int j = 0; j < items.length; j++) {
                string += items[j] + "@";
            }

            string += " " + sales[i].getCost();

            if (i == sales.length - 1) {
                break;
            } else {

                string += ",";
            }
        }
        return string;
    }

    private static void printManagers(ArrayList<Manager> managers) {
        for (Manager manager : managers) {
            System.out.println(manager.getId() + " " + manager.getFio() + " " + manager.getNumber() + " " + manager.getEmail() + " " + getSalesInString(manager.getSales()));
        }
    }
}