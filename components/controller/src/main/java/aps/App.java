package aps;

import javax.sound.midi.Soundbank;
import java.net.*;
/**
 * Hello world!
 *
 */
public class App
{
	public static void main( String[] args )
	{
		//conecção com cliente
		System.out.println("........Iniciando aplicação Controller!");
		try{
			System.out.println("........Connectando ao cliente!!!");
			ServerSocket serverSocket = new ServerSocket(9999);
			Socket socket = serverSocket.accept();
			System.out.println("........Conecção efetuada com sucesso cliente/controller");
		}catch (Exception e){
			System.out.println("........Não foi possivel conectar ao cliente");
			System.out.println(e.getMessage());
		}
	}
}
