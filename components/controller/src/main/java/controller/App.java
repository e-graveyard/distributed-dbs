package controller;
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

        int[] serverPorts = Input.strListToIntList(args);

        Controller c;

        try
        {
            c = new Controller(serverPorts);

            String port = Integer.toString(c.getPort());
            Logger.info("Hello!");
            Logger.info("I'm controller *purple@name*normal".replace("@name", c.getName()));

            Logger.info("Hang tight...");
            boolean okay = c.discover();
            if(!okay)
            {
                Logger.error("Could not discover any available server.");
                Logger.info("Exiting...");
                System.exit(1);
            }

            c.retry();

            Logger.info("Listening on port *purple@port*normal".replace("@port", port));
            c.listen();
        }
        catch(Exception e)
        {
            Logger.error("Something went wrong.");
            e.printStackTrace();
        }
    }
}
