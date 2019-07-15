package ru.eltex;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Socket s = new Socket("172.21.0.154", 1050)) {
            InputStream inStream = s.getInputStream();
            OutputStream outStream = s.getOutputStream();

            System.out.println();
            System.out.println("Client connected to socket.");

            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream);

            String line = in.nextLine();
            System.out.println(line);
            System.out.println("Client sent message " + line + "to server.");

            out.write("Client: Privet, server !");
            out.flush();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
