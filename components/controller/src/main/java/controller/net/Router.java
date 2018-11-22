package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.net.Socket;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

class Router
{
    private int counter;
    private int QUANTITY_OF_SERVERS;

    private Server[] servers;
    private String pingRequest;

    public void checkServersAvailability()
    {
        for(int i = 0; i < servers.length; i++)
        {
            boolean isAvailable = (ping(servers[i].getPort()) != null);
            servers[i].setAvailability(isAvailable);

            if(isAvailable)
            {
                Logger.success("Server *purple" + servers[i].getName() + "*normal is available.");
            }
            else
            {
                Logger.error("Server *purple" + servers[i].getName() + "*normal is unavailable.");
            }
        }
    }

    public void init(Server[] servers)
    {
        this.counter = 0;
        this.QUANTITY_OF_SERVERS = servers.length;

        this.servers = servers;
    }

    public void setPingRequest(String sender)
    {
        Request req = new Request();

        req.setKind("Ping");
        req.setSender(sender);

        this.pingRequest = (new Gson().toJson(req));
    }

    public String request(Socket s, String payload)
    {
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())))
        {
            if(payload != null)
            {
                bw.write(payload);
                bw.write("\n");
                bw.flush();
            }

            return br.readLine();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public String ping(int port)
    {
        try(Socket s = new Socket("127.0.0.1", port))
        {
            return request(s, pingRequest);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public Integer balance()
    {
        int tries = 0;

        while(tries < QUANTITY_OF_SERVERS)
        {
            counter++;

            if(counter == QUANTITY_OF_SERVERS)
                this.counter = 0;

            if(servers[counter].isAvailable())
            {
                return new Integer(counter);
            }

            tries++;
        }

        return null;
    }

    public boolean areAllServersAvailable()
    {
        for(Server s : servers)
        {
            if(!s.isAvailable())
                return false;
        }

        return true;
    }

    public Server getServer(int i)
    {
        return servers[i];
    }
}
