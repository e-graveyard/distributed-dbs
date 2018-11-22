package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.util.Random;
import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

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

        int index = rand.nextInt(humanNames.length);

        String name = humanNames[index];
        String numb = Integer.toString(rand.nextInt(1000000));

        return (name + "-" + numb);
    }

    private int generateValidPort()
    {
        int port = 0;
        while(port < MIN_PORT_NUMBER)
        {
            port = rand.nextInt((MAX_PORT_NUMBER - MIN_PORT_NUMBER) + 1) - MIN_PORT_NUMBER;
        }

        return port;
    }

    public String getName()
    {
        return name;
    }

    public int getPort()
    {
        return port;
    }

    public boolean discover()
    {
        boolean available = false;

        int len = serverPorts.length;
        Server[] servers = new Server[len];

        for(int i = 0; i < len; i++)
        {
            int port = serverPorts[i];
            Logger.info("Trying to discover server at port *purple@port*normal.".replace("@port", Integer.toString(port)));

            String res = router.ping(serverPorts[i]);
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
            router.init(servers);

        return available;
    }

    public void listen() throws IOException
    {
       try(ServerSocket socket = new ServerSocket(port))
        {
            Runtime.getRuntime().addShutdownHook(
                    new Thread()
                    {
                       @Override
                       public void run()
                       {
                           System.out.print("\n");
                           Logger.warning("SIGINT caught.");
                           Logger.info("Exiting...");

                           return;
                       }
                    });

            while(true)
            {
                Socket client = socket.accept();
                new Thread(new Connection(client, router)).start();
            }
        }
    }
}
