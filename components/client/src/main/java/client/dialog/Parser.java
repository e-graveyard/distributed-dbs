package client;
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
    private JsonObject response;

    public Parser(String response)
    {
        this.gson = new Gson();

        if(response == null)
            this.response = null;
        else
            this.response = gson.fromJson(response, JsonObject.class);
    }

    public boolean isOkay()
    {
        return response != null;
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
        return getKeyAsObject(response, "data");
    }

    private JsonObject getEnvelope()
    {
        return getKeyAsObject(response, "envelope");
    }

    public String getSender()
    {
        return getKeyAsString(getEnvelope(), "from");
    }

    public String getMessage()
    {
        return getKeyAsString(getData(), "message");
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
