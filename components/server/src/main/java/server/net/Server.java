package server;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

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
    private Random rand;

    private final int MAX_PORT_NUMBER = 65535;
    private final int MIN_PORT_NUMBER = 5000;

    public Server()
    {
        this.rand = new Random();
        this.port = this.generateValidPort();
        this.name = this.generateUniqueName();
    }

    private String generateUniqueName()
    {
        String[] humanNames = {
            "JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM",
            "DAVID", "RICHARD", "CHARLES", "JOSEPH", "THOMAS",
            "MARY", "LINDA", "ELIZABETH", "SUSAN", "MARGARETH",
            "DOROTHY", "LISA", "NANCY", "DONNA", "MICHELLE"
        };

        int index = this.rand.nextInt(humanNames.length);

        String name = humanNames[index];
        String numb = Integer.toString(this.rand.nextInt(1000000));

        return (name + "-" + numb);
    }

    private int generateValidPort()
    {
        int port = 0;
        while(port < MIN_PORT_NUMBER)
        {
            port = this.rand.nextInt((MAX_PORT_NUMBER - MIN_PORT_NUMBER) + 1) - MIN_PORT_NUMBER;
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
                           System.out.print("\n");
                           Logger.warning("SIGINT caught.");
                           Logger.info("Exiting...");

                           return;
                       }
                    });

            while(true)
            {
                Socket client = socket.accept();
                new Thread(new Connection(client, this)).start();
            }
        }
    }
}
