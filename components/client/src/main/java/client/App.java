package main;

import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class App
{
    public static void main( String[] args ) throws Exception {
        Socket clientSocket = null;
        String menssage = "";


        do {
            System.out.println("........Fazendo connexão ");
            for(int i = 9000; i <= 9999; i++){
            //connecção com o controller
                try{
                    clientSocket = new Socket("127.0.0.1", 9999);
                    break;
                }catch(Exception e){
                    System.out.print(".");
                }
            }
            System.out.println(" ");
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
                menssage = "1";

            } else if ("2".equals(option)) {
                System.out.println("........Remover registro");
                menssage = "2";

            } else if ("3".equals(option)) {
                System.out.println("........Inserir Registro");
                menssage = "3";

            } else if ("4".equals(option)) {
                System.out.println("........Atualizar registro");
                menssage = "4";

            } else if ("0".equals(option)) {
                System.out.println("........Saindo");
                menssage = "0";

            } else {
                System.out.println("Por favor digite uma opção valida");
            }


            //Envio da mensagem
            System.out.println("........Envio da mensagem client/controller");
            try {
                OutputStream out = clientSocket.getOutputStream();

                out.write(menssage.getBytes());

            } catch (Exception e) {

            }

            //encerrar a comunicaÃ§Ã£o
            try {
                clientSocket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }while(!menssage.equals("0"));
    }
}
