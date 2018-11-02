package main;

import java.io.IOException;

public class App
{
    public static void main( String[] args )
    {
        Server s = new Server();

        try
        {
            String message = "\n\nHi there. I'm server @name. Listening on port @port..."
                .replace("@name", s.getName())
                .replace("@port", Integer.toString(s.getPort()));
            System.out.println(message);

            s.listen();
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong...\n");
            e.printStackTrace();
        }
    }
}
