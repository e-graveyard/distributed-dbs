package main;

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

    public Server()
    {
        this.port = Utils.generateValidPort();
        this.name = Utils.generateUniqueName();
        this.connections = new ArrayList<>();
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
