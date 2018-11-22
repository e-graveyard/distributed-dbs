package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.util.Random;

class Controller
{
    private Random rand;
    private String name;
    private Router router;
    private int port;
    private int[] serverPorts;

    private final int MAX_PORT_NUMBER = 65535;
    private final int MIN_PORT_NUMBER = 5000;

    public Controller(int[] serverPorts)
    {
        this.rand = new Random();
        this.name = this.generateUniqueName();
        this.port = this.generateValidPort();
        this.serverPorts = serverPorts;

        this.router = new Router();
        this.router.setPingRequest(this.name);
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

    public boolean discover()
    {
        boolean available = false;

        int len = this.serverPorts.length;
        Server[] servers = new Server[len];

        for(int i = 0; i < len; i++)
        {
            int port = this.serverPorts[i];
            Logger.info("Trying to discover server at port *purple@port*normal.".replace("@port", Integer.toString(port)));

            String res = this.router.ping(this.serverPorts[i]);
            if(res != null)
            {
                available = true;

                Server s = new Server((new Parser(res)).getSender(), port, true);
                Logger.success("Server *purple@name*normal were discovered.".replace("@name", s.getName()));

                servers[i] = s;
            }
            else
            {
                Logger.error("Unable to discover. Skipping..");
            }
        }

        if(available)
            this.router.init(servers);

        return available;
    }

    public void listen()
    {
        Logger.info("Bye-Bye!");
    }
}
