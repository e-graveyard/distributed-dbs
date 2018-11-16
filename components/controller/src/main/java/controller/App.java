package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App extends Thread {
    String message = "";
    //conecção com cliente
    Socket socket = null;
    ServerSocket server;


    private int serveport;

    public static void main( String[] args ) throws IOException {


        App appA = new App(9999);
        appA.start();
    }

    public App(int port){
        serveport = port;
    }

    @Override
    public void run () {

        do {
            System.out.println("........Iniciando aplicação Controller!");
            try {
                server = new ServerSocket(serveport);
                System.out.println("........Connectando ao cliente!!!");

                socket = server.accept();
                System.out.println("........Conecção efetuada com sucesso cliente/controller");
            } catch (Exception e) {
                System.out.println("........Não foi possivel conectar ao cliente");
                System.out.println(e.getMessage());
            }
            InputStream inputStream;
            Scanner scanner;
            System.out.println("........recebendo dados client/controller");
            try {

                inputStream = socket.getInputStream();
                scanner = new Scanner(inputStream);
                message = scanner.nextLine();

                System.out.println("........Mensagem recebida: " + message);
            } catch (Exception e) {
            }

            try {
                server.close();
                socket.close();
            } catch (Exception e) {
                System.out.println("Erro ao encerrar a comunicaÃ§Ã£o.");
                System.out.println(e.getMessage());
            }
        } while (!message.equals("0"));

    }
}
