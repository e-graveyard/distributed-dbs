package main;

public class Server
{
    private String name;
    private int port;

    public Server()
    {
        this.name = Utils.generateUniqueName();
        this.port = Utils.generateValidPort();
    }

    public String getName()
    {
        return this.name;
    }

    public int getPort()
    {
        return this.port;
    }
}
