package ru.klskvmr;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class BigTypes {
    public static void main(String[] args) {
        //Integer a = 4;
        //BigInteger b = new BigInteger(a);

        BigInteger a = new BigInteger("2");
        BigInteger result = a.pow(1000);

        try (FileWriter fw = new FileWriter("src/main/resources/bigTypes.txt")){

        }catch (IOException error){
            System.out.println(error);
        }

    }
}
