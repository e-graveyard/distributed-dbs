package aps;

import javax.sound.midi.Soundbank;
import java.io.InputStream;
import java.net.*;
import java.util.*;
/**
 * Hello world!
 *
 */
public class App
{
	public static void main( String[] args )
	{
		//conecção com cliente
		ServerSocket serverSocket = null;
		Socket socket = null;
		System.out.println("........Iniciando aplicação Controller!");
		try{
			System.out.println("........Connectando ao cliente!!!");
			serverSocket = new ServerSocket(9999);
			socket = serverSocket.accept();
			System.out.println("........Conecção efetuada com sucesso cliente/controller");
		}catch (Exception e){
			System.out.println("........Não foi possivel conectar ao cliente");
			System.out.println(e.getMessage());
		}

		InputStream inputStream;
		Scanner scanner;
		System.out.println("........recebendo dados client/controller");
		try{
			inputStream = socket.getInputStream();
			scanner = new Scanner(inputStream);
			String message = scanner.nextLine();

			System.out.println("........Mensagem recebida: " + message);
		}catch (Exception e){}
	}
}
