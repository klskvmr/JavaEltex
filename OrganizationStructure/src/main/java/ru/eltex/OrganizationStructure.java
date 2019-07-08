package ru.eltex;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class OrganizationStructure {

    public static void main(String[] args) {
        ArrayList<Developer> developers = new ArrayList<Developer>();
        ArrayList<Manager> managers = new ArrayList<Manager>();

        for (int i = 0; i < 10; i++) {
            developers.add(new Developer("Elon Mask" + i, "90" + i, i + "@gmail.com", new String[]{"Java", "C#"}));
        }

        try (FileWriter fw = new FileWriter("developers.csv")) {

            for (Developer developer : developers) {
                fw.write(developer.toCSV());
            }
            fw.flush();
        } catch (IOException error) {
            System.err.println();
        }
    }
}