package main;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

class Request
{
    private Gson gson;
    private String payload;

    public Request(String payload)
    {
        this.payload = payload;
        this.gson = new Gson();
    }

    private JsonElement getKeyValue(String keyName)
    {
        JsonObject obj = this.gson.fromJson(this.payload, JsonObject.class);
        JsonElement key = obj.get(keyName);

        return key;
    }

    public String getKind()
    {
        return this.getKeyValue("kind").getAsString();
    }

    public String pong()
    {
        return this.gson.toJson(new Response("Response", "Pong"));
    }
}
