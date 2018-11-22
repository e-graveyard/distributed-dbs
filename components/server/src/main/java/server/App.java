package server;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.io.IOException;

public class App
{
    public static void main(String[] args)
    {
        Server s = null;
        if(args.length < 1)
        {
            s = new Server(null);
        }
        else
        {
            if(!Input.isArgValid(args))
            {
                Logger.info("Exiting...");
                System.exit(1);
            }
            else
            {
                s = new Server(Integer.valueOf(args[0]));
            }
        }

        try
        {
            String port = Integer.toString(s.getPort());

            Logger.info("Hello!");
            Logger.info("I'm server *purple@name*normal.".replace("@name", s.getName()));
            Logger.info("Listening on port *purple@port*normal.".replace("@port", port));

            s.listen();
        }
        catch(IOException e)
        {
            Logger.error("Something went wrong.\n");
            e.printStackTrace();
        }
    }
}
