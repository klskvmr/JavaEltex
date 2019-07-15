package ru.eltex;

import java.io.IOException;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client is connected");

            byte[] data = "Hello".getBytes();
            InetAddress addr = InetAddress.getByName("172.21.0.154");
            DatagramPacket pack =
                    new DatagramPacket(data, data.length, addr, 1050);
            DatagramSocket ds = new DatagramSocket();
            ds.send(pack);
            ds.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
