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
    }
}
