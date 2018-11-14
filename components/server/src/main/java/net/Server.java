package main;

import java.util.Random;
import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

import java.util.List;
import java.util.ArrayList;

import java.lang.Runtime;
import java.lang.InterruptedException;

class Server
{
    private int port;
    private String name;

    private static Random rand = new Random();
    private static final int MAX_PORT_NUMBER = 65535;
    private static final int MIN_PORT_NUMBER = 5000;

    public Server()
    {
        this.port = generateValidPort();
        this.name = generateUniqueName();
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
        int port = 0;
        while(port < MIN_PORT_NUMBER)
        {
            port = rand.nextInt((MAX_PORT_NUMBER - MIN_PORT_NUMBER) + 1) - MIN_PORT_NUMBER;
        }

        return port;
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
            Runtime.getRuntime().addShutdownHook(
                    new Thread()
                    {
                       @Override
                       public void run()
                       {
                           System.out.println("");
                           Logger.warning("SIGINT caught.");
                           Logger.info("Exiting...");

                           return;
                       }
                    });

            while(true)
            {
                Socket client = socket.accept();
                Connection c = new Connection(client, this);
                new Thread(c).start();
            }
        }
    }
}
