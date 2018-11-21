package controller;
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

class Router
{
    public Router()
    {
    }

    public String makeRequest(String host, int port, String payload)
    {
        try(Socket socket = new Socket(host, port);
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
            return null;
        }
    }
}
