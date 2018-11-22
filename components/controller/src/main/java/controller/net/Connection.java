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

class Connection implements Runnable
{
    private Socket client;
    private Router router;

    public Connection(Socket client, Router router)
    {
        this.client = client;
        this.router = router;
    }

    public Server pickAvailableServer(String kind)
    {
        boolean serversAreAvailable = router.areAllServersAvailable();
        Integer serverIndex = router.balance();

        if(serversAreAvailable)
        {
            Logger.success("All servers are ready to go.");
        }
        else
        {
            if(serverIndex == null)
            {
                Logger.error("Could not find any server available. Ignoring request.");
                return null;
            }

            Logger.warning("One or more servers are not ready.");
            Logger.info("Restricting actions to read-only requests.");

            if(!kind.equals("ReadRecord"))
            {
                Logger.error("Could not proceed with request *purple@kind*normal request.".replace("@kind", kind));
                return null;
            }
        }

        return router.getServer(serverIndex.intValue());
    }

    public void run()
    {
        try(BufferedReader clientReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter clientWriter = new BufferedWriter(new OutputStreamWriter(client.getOutputStream())))
        {
            String clientRequest = clientReader.readLine();
            Parser parsedRequest = new Parser(clientRequest);

            String kind = parsedRequest.getKind(),
                   sender = parsedRequest.getSender();

            Logger.info("Receiving a *purple@kind*normal request from client *purple@sender*normal."
                    .replace("@kind", kind)
                    .replace("@sender", sender));

            Server server = pickAvailableServer(kind);
            if(server == null)
                return;

            Logger.info("Routing request *purple@kind*normal (*purple@client*normal -> *purple@server*normal)."
                    .replace("@kind", kind)
                    .replace("@client", sender)
                    .replace("@server", server.getName()));

            try(Socket serverSocket = new Socket("127.0.0.1", server.getPort()))
            {
                String serverResponse = router.request(serverSocket, clientRequest);

                clientWriter.write(serverResponse);
                clientWriter.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
