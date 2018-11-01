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

    public String getKind()
    {
        JsonObject obj = this.gson.fromJson(this.payload, JsonObject.class);
        JsonElement kind = obj.get("kind");

        return kind.getAsString();
    }
}
