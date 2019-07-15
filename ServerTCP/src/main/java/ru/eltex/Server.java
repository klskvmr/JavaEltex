package ru.eltex;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        try (ServerSocket s = new ServerSocket(1050)) {
            Socket client = s.accept();
            InputStream inStream = client.getInputStream();
            System.out.println("DataInputStream created");
            OutputStream outStream = client.getOutputStream();
            System.out.println("DataOutputStream  created");


            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream);

            out.write("Server: Hello, client !");
            out.flush();
            System.out.println("Server sent message to client.");

            System.out.println("reading...");
            String string = in.nextLine();
            System.out.println("check");
            out.flush();
            System.out.println(string);


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
