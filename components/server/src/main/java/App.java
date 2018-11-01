package main;

public class App
{
	public static void main( String[] args )
	{
        Server s = new Server();

        String message = "\n\nHi there. I'm server @name. Listening on port @port...";

        message = message
            .replace("@name", s.getName())
            .replace("@port", Integer.toString(s.getPort()));

        System.out.println(message);

        s.listen();
	}
}
