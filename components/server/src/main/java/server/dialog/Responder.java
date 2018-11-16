package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

class Responder
{
    private Gson gson;
    private Response response;

    public Responder(String sender, String recipient)
    {
        this.gson = new Gson();

        this.response = new Response();
        this.response.setEnvelope(sender, recipient);
    }

    public String pong()
    {
        response.setSuccess(true);
        response.putData("message", "pong");
        return gson.toJson(response);
    }

    public String created()
    {
        response.setSuccess(true);
        response.putData("message", "Book registered");
        return gson.toJson(response);
    }

    public String readed(Book book)
    {
        response.setSuccess(true);
        response.putData("message", "Book information readed");

        response.putData("title",       book.getTitle());
        response.putData("author",      book.getAuthor());
        response.putData("publication", book.getPublication());
        response.putData("isbn",        book.getIsbn());
        response.putData("pages",       Integer.toString(book.getPages()));

        return gson.toJson(response);
    }

    public String updated()
    {
        response.setSuccess(true);
        response.putData("message", "Book information updated");
        return gson.toJson(response);
    }

    public String deleted()
    {
        response.setSuccess(true);
        response.putData("message", "Book information deleted");
        return gson.toJson(response);
    }

    public String creationError()
    {
        response.setSuccess(false);
        response.putData("message", "Unable to register the book");
        return gson.toJson(response);
    }

    public String readError()
    {
        response.setSuccess(false);
        response.putData("message", "Unable to read the register of the book");
        return gson.toJson(response);
    }

    public String updateError()
    {
        response.setSuccess(false);
        response.putData("message", "Unable to update the register of the book");
        return gson.toJson(response);
    }

    public String deletionError()
    {
        response.setSuccess(false);
        response.putData("message", "Unable to delete the register of the book");
        return gson.toJson(response);
    }
}
