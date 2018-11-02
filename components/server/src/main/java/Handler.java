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

    public String getKeyAsString(JsonObject obj, String key)
    {
        return obj.get(key).getAsString();
    }

    public JsonObject getKeyAsObject(JsonObject obj, String key)
    {
        return obj.get(key).getAsJsonObject();
    }

    public String getKind()
    {
        return getKeyAsString(payload, "kind");
    }

    public String getSender()
    {
        JsonObject envelope = getKeyAsObject(payload, "envelope");
        return getKeyAsString(envelope, "from");
    }

    public Book getBookInformation()
    {
        JsonObject data = getKeyAsObject(payload, "data");
        Book book = gson.fromJson(data.toString(), Book.class);

        return book;
    }

    public String pong()
    {
        return gson.toJson(new Response("Response", "Pong"));
    }
}
