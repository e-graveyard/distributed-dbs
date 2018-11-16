package server;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.net.Socket;
import java.util.Scanner;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Connection implements Runnable
{
    private Socket client;
    private Server server;

    public Connection(Socket client, Server server)
    {
        this.client = client;
        this.server = server;
    }

    private String handle(Parser message)
    {
        // Resposta do servidor.
        String response = null;

        // Status de sucesso da operação no banco.
        Boolean success = null;

        // ISBN (primary key) do livro (pode ou não ser usado).
        String isbn = null;

        // Objeto do livro (pode ou não ser usado).
        Book book = null;

        // Objeto do banco de dados.
        Database db = new Database();

        // Quem é o remetente?
        String sender = message.getSender();

        // Objeto da resposta, inicializa com envelope formado.
        Responder responder = new Responder(this.server.getName(), sender);

        switch(message.getKind())
        {
            case "Ping":
                Logger.info("Server has received a ping from *purple"
                    + sender + "*normal.");

                response = responder.pong();
                break;

            case "CreateRecord":
                book = message.getBookInformation();

                Logger.info("Server has received a record creation request from *purple" + sender + "*normal.");
                Logger.info("Book title: *purple" + book.getTitle() + "*normal.");
                Logger.info("Book author: *purple" + book.getAuthor() + "*normal.");

                success = db.create(book);
                if(success)
                {
                    Logger.success("Registered!");
                    response = responder.created();
                }
                else
                {
                    Logger.error("Could not register.");
                    response = responder.creationError();
                }
                break;

            case "ReadRecord":
                isbn = message.getIsbn();

                Logger.info("Server has received a request to read a register from *purple" + sender + "*normal.");
                Logger.info("Book ISBN: *purple" + isbn + "*normal.");

                book = db.read(isbn);
                success = (book != null);
                if(success)
                {
                    Logger.success("Readed!");
                    response = responder.readed(book);
                }
                else
                {
                    Logger.error("Could not read.");
                    response = responder.readError();
                }
                break;

            case "UpdateRecord":
                book = message.getBookInformation();

                Logger.info("Server has received a request to update a register from *purple" + sender + "*normal.");
                Logger.info("Book ISBN: *purple" + book.getIsbn() + "*normal.");

                success = db.update(book);
                if(success)
                {
                    Logger.success("Updated!");
                    response = responder.updated();
                }
                else
                {
                    Logger.error("Could not update.");
                    response = responder.updateError();
                }
                break;

            case "DeleteRecord":
                isbn = message.getIsbn();

                Logger.info("Server has received a request to read a register from *purple" + sender + "*normal.");
                Logger.info("Book ISBN: *purple" + isbn + "*normal.");

                success = db.delete(isbn);
                if(success)
                {
                    Logger.success("Removed!");
                    response = responder.deleted();
                }
                else
                {
                    Logger.error("Could not delete.");
                    response = responder.deletionError();
                }
                break;

            default:
                Logger.warning("Server has received an unknown message kind from *purple" + sender + "*normal. Ignoring.");
                break;
        }

        return response;
    }

    public void run()
    {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.client.getOutputStream())))
        {
            String response = this.handle(new Parser(br.readLine()));

            bw.write(response.toString());
            bw.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
