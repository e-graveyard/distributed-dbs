package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.util.Random;
import com.google.gson.Gson;

class Controller
{
    private int port;
    private String name;
    private Random rand;
    private Router router;
    private Server[] servers;

    public Controller(int[] serverPorts)
    {
        this.rand    = new Random();
        this.router  = new Router();
        this.port    = this.generateValidPort();
        this.name    = this.generateUniqueName();
        this.servers = this.discover(serverPorts);
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

    public String getName()
    {
        return this.name;
    }

    public int getPort()
    {
        return this.port;
    }

    private Server ping(String host, int port)
    {
        Request req = new Request();

        req.setKind("Ping");
        req.setSender("");

        String res = router.makeRequest(
                host, port,
                (new Gson()).toJson(req));

        String serverName = (new Parser(res)).getSender();

        return new Server(serverName, port);
    }

    private Server[] discover(int[] ports)
    {
        Server[] servers = new Server[ports.length];

        for(int i = 0; i < ports.length; i++)
        {
            servers[i] = ping("127.0.0.1", ports[i]);
        }

        return servers;
    }
}
