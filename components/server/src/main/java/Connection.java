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
        String sender = handler.getSender();
        String response = null;
        switch(handler.getKind())
        {
            case "Ping":
                Logger.info("Server has received a ping from *purple"
                    + sender + "*normal.");

                response = handler.pong();
                break;

            case "CreateRecord":
                Book book = handler.getBookInformation();

                Database db = new Database();
                db.create(book);
                break;

            case "GetRecord":
                break;

            default:
                Logger.warning("Unknown message kind. Ignoring.");
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
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
