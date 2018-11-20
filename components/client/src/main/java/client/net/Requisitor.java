package client;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.net.Socket;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

class Requisitor
{
    private Gson gson;
    private String clientName;
    private int controllerPort;

    public Requisitor(Client client)
    {
        this.gson = new Gson();
        this.clientName = client.getName();
        this.controllerPort = client.getControllerPort();
    }

    private String makeRequest(String payload)
    {
        try(Socket socket = new Socket("127.0.0.1", controllerPort);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream())))
        {
            bw.write(payload);
            bw.write("\n");
            bw.flush();

            return br.readLine();
        }
        catch(IOException e)
        {
            e.printStackTrace();

            return null;
        }
    }

    public Parser createBook(String title, String publ, String author, String pages, String isbn)
    {
        Request req = new Request();

        req.setSender(clientName);
        req.setKind("CreateRecord");

        req.putData("title",       title);
        req.putData("publication", publ);
        req.putData("author",      author);
        req.putData("pages",       pages);
        req.putData("isbn",        isbn);

        String res = makeRequest(gson.toJson(req));

        return new Parser(res);
    }

    public Parser readBook(String isbn)
    {
        Request req = new Request();

        req.setSender(clientName);
        req.setKind("ReadRecord");
        req.putData("isbn", isbn);

        String res = makeRequest(gson.toJson(req));

        return new Parser(res);
    }
}
