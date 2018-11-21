package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;

class Parser
{
    private Gson gson;
    private JsonObject content;

    public Parser(String content)
    {
        this.gson = new Gson();
        this.content = gson.fromJson(content, JsonObject.class);
    }

    private String getKeyAsString(JsonObject obj, String key)
    {
        return obj.get(key).getAsString();
    }

    private JsonObject getKeyAsObject(JsonObject obj, String key)
    {
        return obj.get(key).getAsJsonObject();
    }
    private JsonObject getEnvelope()
    {
        return getKeyAsObject(content, "envelope");
    }

    public String getSender()
    {
        return getKeyAsString(getEnvelope(), "from");
    }
}
