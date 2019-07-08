package ru.eltex;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrganizationStructure {

    private static void writeDevelopers(ArrayList<Developer> developers) {
        try (FileWriter fw = new FileWriter("developers.csv")) {
            for (Developer developer : developers) {
                fw.write(developer.toCSV());
            }

            fw.flush();
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    private static void writeManagers(ArrayList<Manager> managers) {
        try (FileWriter fw = new FileWriter("managers.csv")) {
            for (Manager manager : managers) {
                fw.write(manager.toCSV());
            }

            fw.flush();
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    private static ArrayList<Developer> readDevelopers() {
        ArrayList<Developer> developers = new ArrayList<Developer>();

        try (FileReader fr = new FileReader("developers.csv"); Scanner readFile = new Scanner(fr)) {
            while (readFile.hasNextLine()) {
                String[] strings = readFile.nextLine().split(";");

                String[] languages = strings[strings.length - 1].split(",");

                developers.add(new Developer(strings[0], strings[1], strings[2],
                        strings[strings.length - 1].split(",")));
            }
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }

        return developers;
    }

    private static void readManagers() {
        ArrayList<Manager> managers = new ArrayList<>();

        try (Scanner readFile = new Scanner(new FileInputStream("managers.csv"))) {
            while (readFile.hasNextLine()) {
                String[] strings = readFile.nextLine().split(";");

            }

        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    public static void main(String[] args) {
        ArrayList<Developer> developers = new ArrayList<Developer>();
        ArrayList<Manager> managers = new ArrayList<Manager>();

        for (int i = 0; i < 10; i++) {
            developers.add(new Developer("Elon Mask" + i, "90" + i, i + "@gmail.com", new String[]{"Java", "C#"}));
        }

        for (int i = 0; i < 10; i++) {
            managers.add(new Manager("Victor Pelevin" + i, "97" + i, i + "@gmail.com", new Sale[]{
                    new Sale(new String[]{"item1", "item2", "item3"}, 600.5 + i), new Sale(new String[]{"item4", "item5", "item6"}, 700.5 + i)}));
        }

        writeDevelopers(developers);
        writeManagers(managers);
    }
}