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

    private String handle(Handler handler)
    {
        String response = null;
        switch(handler.getKind())
        {
            case "Ping":
                Logger.info("Server has received a ping.");
                response = handler.pong();
                break;

            case "CreateRecord":
                break;

            case "GetRecord":
                break;
        }

        return response;
    }

    public void run()
    {
        try(BufferedReader data = new BufferedReader(new InputStreamReader(this.client.getInputStream())))
        {
            String message, response;
            while((message = data.readLine()) != null)
            {
                response = this.handle(new Handler(message));
                System.out.println(response);
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
