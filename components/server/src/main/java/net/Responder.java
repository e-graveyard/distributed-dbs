package main;

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

    public String ping()
    {
        response.setSuccess(true);
        response.setData("message", "pong");
        return gson.toJson(response);
    }

    public String created()
    {

        response.setSuccess(true);
        response.setData("message", "Book registered");
        return gson.toJson(response);
    }

    public String readed(Book book)
    {

        response.setSuccess(true);
        response.setData("message", "Book information readed");

        response.setData("title",       book.getTitle());
        response.setData("author",      book.getAuthor());
        response.setData("publication", book.getPublication());
        response.setData("isbn",        book.getIsbn());
        response.setData("pages",       Integer.toString(book.getTitle()));

        return gson.toJson(response);
    }

    public String updated()
    {

        response.setSuccess(true);
        response.setData("message", "Book information updated");
        return gson.toJson(response);
    }

    public String deleted()
    {

        response.setSuccess(true);
        response.setData("message", "Book information deleted");
        return gson.toJson(response);
    }

    public String creationError()
    {
        response.setSuccess(false);
        response.setData("message", "Unable to register the book");
        return gson.toJson(response);
    }

    public String readError()
    {
        response.setSuccess(false);
        response.setData("message", "Unable to read the register of the book");
        return gson.toJson(response);
    }

    public String updateError()
    {
        response.setSuccess(false);
        response.setData("message", "Unable to update the register of the book");
        return gson.toJson(response);
    }

    public String deletionError()
    {
        response.setSuccess(false);
        response.setData("message", "Unable to delete the register of the book");
        return gson.toJson(response);
    }
}
