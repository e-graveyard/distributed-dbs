package server;

import java.util.HashMap;

class Response
{
    private String kind;
    private boolean success;
    private HashMap<String, String> envelope;
    private HashMap<String, String> data;

    public Response()
    {
        this.kind = "Response";
        this.data = new HashMap<>();
        this.envelope = new HashMap<>();
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public void setEnvelope(String sender, String recipient)
    {
        this.envelope.put("from", sender);
        this.envelope.put("to", recipient);
    }

    public void putData(String key, String value)
    {
        this.data.put(key, value);
    }
}
