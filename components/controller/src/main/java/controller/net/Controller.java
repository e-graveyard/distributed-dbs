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
    private int[] serverPorts;

    private final int MAX_PORT_NUMBER = 65535;
    private final int MIN_PORT_NUMBER = 5000;

    public Controller(int[] serverPorts)
    {
        this.rand   = new Random();
        this.name   = this.generateUniqueName();
        this.port   = this.generateValidPort();
        this.router = new Router();
        this.serverPorts = serverPorts;
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

    private int generateValidPort()
    {
        int port = 0;
        while(port < MIN_PORT_NUMBER)
        {
            port = this.rand.nextInt((MAX_PORT_NUMBER - MIN_PORT_NUMBER) + 1) - MIN_PORT_NUMBER;
        }

        return port;
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
        req.setSender(this.name);

        String res = router.makeRequest(
                host, port,
                (new Gson()).toJson(req));

        if(res == null)
            return null;

        String serverName = (new Parser(res)).getSender();

        return new Server(serverName, port);
    }

    public void discover()
    {
        int len = this.serverPorts.length;

        Server[] servers = new Server[len];

        for(int i = 0; i < len; i++)
        {
            int port = this.serverPorts[i];
            Logger.info("Trying to discover server at port *purple@port*normal.".replace("@port", Integer.toString(port)));

            Server s = ping("127.0.0.1", this.serverPorts[i]);
            if(s != null)
            {
                Logger.success("Server *purple@name*normal were discovered.".replace("@name", s.getName()));
                servers[i] = s;
            }
            else
            {
                Logger.error("Unable to discover. Skipping..");
            }
        }

        this.servers = servers;
    }

    public void listen()
    {
        Logger.info("Bye-Bye!");
    }
}
