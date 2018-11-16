package client;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */


public class App
{
    public static void main(String[] args)
    {
        if(!Input.isArgValid(args))
        {
            Logger.info("Exiting...");
            System.exit(1);
        }

        int controllerPort = Integer.parseInt(args[0]);
        Client c = new Client(controllerPort);

        try
        {
            Logger.info("Hello!");
            Logger.info("I'm client *purple@name*normal.".replace("@name", c.getName()));

            c.act();
        }
        catch(Exception e)
        {
            Logger.error("Something went wrong.\n");
            e.printStackTrace();
        }

    }
}
