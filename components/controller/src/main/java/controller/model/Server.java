package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

class Server
{
    private String name;
    private int port;
    private boolean available;

    public Server(String name, int port, boolean available)
    {
        this.name = name;
        this.port = port;
        this.available = available;
    }

    public String getName()
    {
        return this.name;
    }

    public int getPort()
    {
        return this.port;
    }

    public void setAvailability(boolean available)
    {
        this.available = available;
    }

    public boolean isAvailable()
    {
        return this.available;
    }
}
