package aps;

import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class App
{
    public static void main( String[] args )
    {
        Socket clientSocket = null;
        //connecção com o controller
        try{
            System.out.println("........Fazendo connecção ");
            clientSocket = new Socket("127.0.0.1", 9999);
        }catch(Exception e){
            System.out.println("........Não foi possivel efetuar a conecção Cliente/Controller");
            System.out.println(e.getMessage());
        }

        //Envio da mensagem
        System.out.println("........Envio da mensagem client/controller");
        try{
            Scanner in = new Scanner(System.in);
            String menssage = in.nextLine();
            OutputStream out = clientSocket.getOutputStream();

            out.write(menssage.getBytes());

        }catch(Exception e){

        }
    }
}
