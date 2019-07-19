package ru.eltex;

import java.util.Arrays;
import java.util.Scanner;

public class WeekDays {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("Days of week: " + Arrays.toString(WeekDay.values()));
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the day of the week: ");
        String day = scanner.nextLine();

        switch (WeekDay.valueOf(day.toUpperCase())){
            case MONDAY:
            case TUESDAY:
            case WEDNESDAY:
            case THURSDAY:
                System.out.println(WeekDay.valueOf(day.toUpperCase()).title + " - день тяжелый");
        }

        System.out.print("From " + WeekDay.valueOf(day.toUpperCase()).toString().toLowerCase() + " until Friday: ");

        switch (WeekDay.valueOf(day.toUpperCase())) {
            case SUNDAY:
                System.out.println(WeekDay.FRIDAY.ordinal() - WeekDay.SUNDAY.ordinal() + " days.");
                break;
            case MONDAY:
                System.out.println(WeekDay.FRIDAY.ordinal() - WeekDay.MONDAY.ordinal() + " days.");
                break;
            case TUESDAY:
                System.out.println(WeekDay.FRIDAY.ordinal() - WeekDay.TUESDAY.ordinal() + " days.");
                break;
            case WEDNESDAY:
                System.out.println(WeekDay.FRIDAY.ordinal() - WeekDay.WEDNESDAY.ordinal() + " days.");
                break;
            case THURSDAY:
                System.out.println(WeekDay.FRIDAY.ordinal() - WeekDay.THURSDAY.ordinal() + " day.");
                break;
            case FRIDAY:
                System.out.println("0 days!!");
                break;
            default:
                System.out.println("6 days.");
        }
    }
}