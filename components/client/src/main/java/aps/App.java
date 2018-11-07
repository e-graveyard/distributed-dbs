package aps;

import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) throws Exception {
        Socket clientSocket = null;
        //connecção com o controller
        try{
            System.out.println("........Fazendo connecção ");
            clientSocket = new Socket("127.0.0.1", 9999);
        }catch(Exception e){
            System.out.println("........Não foi possivel efetuar a conecção Cliente/Controller");
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }

        System.out.println("Escolha uma opção");
        System.out.println("1 - Consultar os registros armazenados");
        System.out.println("2 - remover registro");
        System.out.println("3 - inserir registro");
        System.out.println("4 - atualizar registro");
        System.out.println("0 - sair");
        Scanner inOption = new Scanner(System.in);
        String option = inOption.nextLine();
        if ("1".equals(option)) {
            System.out.println("........Consultando registros armazenados");

        } else if ("2".equals(option)) {
            System.out.println("........Remover registro");

        } else if ("3".equals(option)) {
            System.out.println("........Inserir Registro");

        } else if ("4".equals(option)) {
            System.out.println("........Atualizar registro");

        } else if ("0".equals(option)) {
            System.out.println("........Saindo");

        } else {
            System.out.println("Por favor digite uma opção valida");
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
