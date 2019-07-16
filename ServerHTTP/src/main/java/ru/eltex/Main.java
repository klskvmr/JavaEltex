package ru.eltex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (ServerSocket s = new ServerSocket(1050)) {
            while (true) {
                Socket client = s.accept();
                System.out.println("Client is connected");

                new Thread(() -> {
                    try {
                        //InputStream inStream = client.getInputStream();
                        OutputStream outStream = client.getOutputStream();
                        //Scanner in = new Scanner(inStream);
                        PrintWriter out = new PrintWriter(outStream);

                        Scanner inputFile = new Scanner(new FileInputStream("index.html"));
                        String html = "";
                        while (inputFile.hasNextLine()) {
                            html += inputFile.nextLine().replaceAll("\\s+", "");
                        }

                        String resultString = "HTTP/1.0 200 OK\nContent-Type: text/html\nContent-length: " +
                                html.length() + "\n\n" + html;

                        out.write(resultString);
                        out.flush();

                        client.close();
                        System.out.println("Client is closed");
                        System.out.println();
                    } catch (IOException error) {
                        System.err.println(error.getMessage());
                    }
                }).start();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
