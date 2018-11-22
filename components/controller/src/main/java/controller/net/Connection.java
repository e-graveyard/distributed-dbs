package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.net.Socket;

class Connection implements Runnable
{
    private Socket client;
    private Router router;

    public Connection(Socket client, Router router)
    {
        this.client = client;
        this.router = router;
    }

    public void run()
    {
        String clientRequest = router.request(client, null),
               requestKind   = (new Parser(clientRequest)).getKind();

        if(!router.areAllServersAvailable && requestKind != "ReadRecord")
        {
            // impossible
        }
    }
}
