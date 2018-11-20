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

    public Book readBook(String isbn)
    {
        Request request = new Request();

        request.setSender(clientName);
        request.setKind("ReadRecord");
        request.putData("isbn", isbn);

        String response = makeRequest(gson.toJson(request));

        return null;
        // return (new Parser(response)).toBook();
    }
}
