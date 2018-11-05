package main;

import java.util.HashMap;

class Response
{
    private String kind;
    private HashMap<String, String> data;

    public Response(String kind, String message)
    {
        this.kind = kind;
        this.data = new HashMap<>();
        this.data.put("message", message);
    }
}
