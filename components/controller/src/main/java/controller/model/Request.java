package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.util.HashMap;

class Request
{
    private String kind;
    private HashMap<String, String> envelope;

    public Request()
    {
        this.envelope = new HashMap<>();
    }

    public void setKind(String kind)
    {
        this.kind = kind;
    }

    public void setSender(String sender)
    {
        this.envelope.put("from", sender);
    }
}
