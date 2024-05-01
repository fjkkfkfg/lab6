package test;

import client.managers.SocketClient;
import client.tools.Ask;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        SocketClient client = new SocketClient("localhost",8080);
        client.start();
    }
}