package ru.eltex;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите выражение: ");
        String inputString = in.nextLine();

        String[] tmpStr = inputString.split(" ");

        final double epsilon = 1.e-10;

        double firstNumber = Double.parseDouble(tmpStr[0]);
        double secondNumber = Double.parseDouble(tmpStr[2]);

        double result = 0;

        switch (tmpStr[1]) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (Math.abs(secondNumber) <= epsilon) {
                    System.out.println("Деление на ноль!!");
                    return;
                } else if (Math.abs(firstNumber) <= epsilon) {
                    result = 0;
                } else {
                    result = firstNumber / secondNumber;
                }
        }
        System.out.print("Результат = " + result);
    }
}