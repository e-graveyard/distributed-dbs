package server;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        Server s = new Server();

        try
        {
            Logger.info("Hello!");
            Logger.info("I'm server *purple@name*normal."
                    .replace("@name", s.getName()));
            Logger.info("Listening on port *purple@port*normal."
                    .replace("@port", Integer.toString(s.getPort())));

            s.listen();
        }
        catch(IOException e)
        {
            Logger.error("Something went wrong.\n");
            e.printStackTrace();
        }
    }
}
