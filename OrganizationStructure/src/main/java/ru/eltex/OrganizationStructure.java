package ru.eltex;

import java.util.ArrayList;

public class OrganizationStructure {

    public static void main(String[] args) {
        ArrayList<Developer> developers = new ArrayList<Developer>();
        ArrayList<Manager> managers = new ArrayList<Manager>();

        for (int i = 0; i < 10; i++) {
            developers.add(new Developer("Elon Mask" + i, "90" + i, i + "@gmail.com", new String[]{"Java", "C#"}));
        }
        System.out.println("managers:");
        for (Developer developer : developers) {
            System.out.println(developer.getFio() + " " + developer.getNumber() + " " + developer.getEmail() + " " + developer.getLanguaches());
        }
    }
}
