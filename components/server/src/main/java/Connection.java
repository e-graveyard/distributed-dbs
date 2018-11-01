package main;

import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Connection implements Runnable
{
    private Socket client;
    private Server server;

    public Connection(Socket client, Server server)
    {
        this.client = client;
        this.server = server;
    }

    public void run()
    {
        try(BufferedReader data = new BufferedReader(new InputStreamReader(this.client.getInputStream())))
        {
            String message;
            while((message = data.readLine()) != null)
            {
                System.out.println(message);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
