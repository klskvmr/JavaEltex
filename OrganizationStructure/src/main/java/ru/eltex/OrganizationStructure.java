package ru.eltex;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrganizationStructure {
    public static void main(String[] args) throws IOException {
        ArrayList<Developer> developers = new ArrayList<Developer>();
        ArrayList<Manager> managers = new ArrayList<Manager>();

        for (int i = 0; i < 10; i++) {
            developers.add(new Developer(i, "Elon Mask" + i, "90" + i, i + "@gmail.com", new String[]{"Java", "C#"}));
        }

        for (int i = 0; i < 10; i++) {
            managers.add(new Manager(i, "Victor Pelevin" + i, "97" + i, i + "@gmail.com", new Sale[]{
                    new Sale(new String[]{"item1", "item2", "item3"}, 600.5 + i), new Sale(new String[]{"item4", "item5", "item6"}, 700.5 + i)}));
        }

        //writeDevelopersToJSON(developers);
        readDevelopersFromJSON(developers);

        //writeDevelopersCSV(developers);
        //readDevelopersFromCSV(developers);

        System.out.println();

        //writeManagersToJSON(managers);
        readManagersFromJSON(managers);

        //writeManagersToCSV(managers);
        //readManagersFromCSV(managers);

        printDevelopers(developers);
        printManagers(managers);
    }

    private static void writeDevelopersToCSV(ArrayList<Developer> developers) {
        try (FileWriter fw = new FileWriter("developers.csv")) {
            for (Developer developer : developers) {
                fw.write(developer.toCSV());
            }

            fw.flush();
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    private static void writeManagersToCSV(ArrayList<Manager> managers) {
        try (FileWriter fw = new FileWriter("managers.csv")) {
            for (Manager manager : managers) {
                fw.write(manager.toCSV());
            }

            fw.flush();
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }
    }

    private static void writeDevelopersToJSON(ArrayList<Developer> developers) {
        try (FileWriter fw = new FileWriter("developers.json")) {
            for (Developer developer : developers) {
                fw.write(developer.toJSON());
                fw.write("\n");
            }
            fw.flush();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    private static void writeManagersToJSON(ArrayList<Manager> managers) {
        try (FileWriter fw = new FileWriter("managers.json")) {
            for (Manager manager : managers) {
                fw.write(manager.toJSON());
            }
            fw.flush();
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }

    }

    private static ArrayList<Developer> readDevelopersFromCSV(ArrayList<Developer> developers) {
        developers.clear();

        try (FileReader fr = new FileReader("developers.csv"); Scanner readFile = new Scanner(fr)) {
            while (readFile.hasNextLine()) {
                Developer developer = new Developer();
                developer.fromCSV(readFile.nextLine());

                developers.add(developer);
            }
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }

        return developers;
    }

    private static ArrayList<Manager> readManagersFromCSV(ArrayList<Manager> managers) {
        managers.clear();

        try (Scanner readFile = new Scanner(new FileInputStream("managers.csv"))) {
            while (readFile.hasNextLine()) {
                Manager manager = new Manager();
                manager.fromCSV(readFile.nextLine());

                managers.add(manager);
            }

        } catch (IOException error) {
            System.err.println(error.getMessage());
        }

        return managers;
    }

    public static ArrayList<Developer> readDevelopersFromJSON(ArrayList<Developer> developers) {
        developers.clear();

        try (Scanner inputFile = new Scanner(new FileInputStream("developers.json"))) {
            while (inputFile.hasNextLine()) {
                Developer developer = new Developer();
                developer.fromJSON(inputFile.nextLine());

                developers.add(developer);
            }
        } catch (IOException error) {
            System.err.println(error.getMessage());
        }

        return developers;
    }

    public static ArrayList<Manager> readManagersFromJSON (ArrayList<Manager> managers){
        managers.clear();
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