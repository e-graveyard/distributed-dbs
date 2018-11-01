package main;

import java.net.Socket;
import java.util.Scanner;
import java.io.IOException;

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
        try(Scanner s = new Scanner(this.client.getInputStream()))
        {
            while(s.hasNextLine())
            {
                System.out.println(s.nextLine());
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
