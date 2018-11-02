package main;

import java.util.Random;
import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

import java.util.List;
import java.util.ArrayList;

public class Server
{
    private int port;
    private String name;
    private List<Socket> connections;

    private static Random rand = new Random();
    private static final int MAX_PORT_NUMBER = 65535;
    private static final int MIN_PORT_NUMBER = 5000;

    public Server()
    {
        this.port = generateValidPort();
        this.name = generateUniqueName();
        this.connections = new ArrayList<>();
    }

    public static String generateUniqueName()
    {
        String[] humanNames = {
            "JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM",
            "DAVID", "RICHARD", "CHARLES", "JOSEPH", "THOMAS",
            "MARY", "LINDA", "ELIZABETH", "SUSAN", "MARGARETH",
            "DOROTHY", "LISA", "NANCY", "DONNA", "MICHELLE"
        };

        int index = rand.nextInt(humanNames.length);

        String name = humanNames[index];
        String numb = Integer.toString(rand.nextInt(1000000));

        return (name + "-" + numb);
    }

    public static int generateValidPort()
    {
        return rand.nextInt((MAX_PORT_NUMBER - MIN_PORT_NUMBER) + 1) - MIN_PORT_NUMBER;
    }

    public String getName()
    {
        return this.name;
    }

    public int getPort()
    {
        return this.port;
    }

    public void listen() throws IOException
    {
        try(ServerSocket socket = new ServerSocket(this.port))
        {
            while(true)
            {
                Socket client = socket.accept();
                System.out.println("New connection established.");

                this.connections.add(client);
                Connection c = new Connection(client, this);
                new Thread(c).start();
            }

        }

    }
}
