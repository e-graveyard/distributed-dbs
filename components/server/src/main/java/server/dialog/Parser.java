package server;
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
    private JsonObject payload;

    public Parser(String payload)
    {
        this.gson = new Gson();
        this.payload = gson.fromJson(payload, JsonObject.class);
    }

    private String getKeyAsString(JsonObject obj, String key)
    {
        return obj.get(key).getAsString();
    }

    private JsonObject getKeyAsObject(JsonObject obj, String key)
    {
        return obj.get(key).getAsJsonObject();
    }

    private JsonObject getData()
    {
        return getKeyAsObject(payload, "data");
    }

    private JsonObject getEnvelope()
    {
        return getKeyAsObject(payload, "envelope");
    }

    public String getKind()
    {
        return getKeyAsString(payload, "kind");
    }

    public String getSender()
    {
        return getKeyAsString(getEnvelope(), "from");
    }

    public String getIsbn()
    {
        return getKeyAsString(getData(), "isbn");
    }

    public Book toBook()
    {
        JsonObject data = getData();

        String title  = getKeyAsString(data, "title"),
               author = getKeyAsString(data, "author"),
               publ   = getKeyAsString(data, "publication"),
               isbn   = getKeyAsString(data, "isbn");

        int pages = Integer.parseInt(getKeyAsString(data, "pages"));

        return new Book(title, author, publ, isbn, pages);
    }
}
