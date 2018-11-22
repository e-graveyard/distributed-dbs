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

    private String makeRequest(int port, String payload)
    {
        try(Socket socket = new Socket("127.0.0.1", port);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            bw.write(payload);
            bw.write("\n");
            bw.flush();

            return br.readLine();
        }
        catch(IOException e)
        {
            return null;
        }
    }

    public String ping(int port)
    {
        return makeRequest(port, pingRequest);
    }

    private void refreshServersAvailabilityStatus()
    {
        for(int i = 0; i < servers.length; i++)
            servers[i].setAvailability(ping(servers[i].getPort()) == null);
    }

    private boolean areAllServersAvailable()
    {
        for(Server s : servers)
        {
            if(!s.isAvailable())
                return false;
        }

        return true;
    }

    private Integer balance()
    {
        int tries = 0;

        while(tries < QUANTITY_OF_SERVERS)
        {
            if(counter == QUANTITY_OF_SERVERS)
                counter = 0;

            if(servers[counter].isAvailable())
            {
                return new Integer(counter);
            }

            counter++;
            tries++;
        }

        return null;
    }
}
