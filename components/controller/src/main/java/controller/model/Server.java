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

    public Balancer(String name, int port)
    {
        this.name = name;
        this.port = port;
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
