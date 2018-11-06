package aps;

import java.net.*;

public class App
{
    public static void main( String[] args )
    {
        //connecção com o controller
        try{
            System.out.println("........Fazendo connecção ");
            Socket clientSocket = new Socket("127.0.0.1", 9999);
        }catch(Exception e){
            System.out.println("........Não foi possivel efetuar a conecção Cliente/Controller");
            System.out.println(e.getMessage());
        }

        //Envio da mensagem
        try{

        }catch(Exception e){

        }
    }
}
