package main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

class Handler
{
    private Gson gson;
    private JsonObject payload;

    public Handler(String payload)
    {
        this.gson = new Gson();
        this.payload = gson.fromJson(payload, JsonObject.class);
    }

    public String getKind()
    {
        return payload.get("kind").getAsString();
    }

    public String getSender()
    {
        JsonObject envelope = payload.get("envelope").getAsJsonObject();
        return envelope.get("from").getAsString();
    }

    public String pong()
    {
        return gson.toJson(new Response("Response", "Pong"));
    }
}
