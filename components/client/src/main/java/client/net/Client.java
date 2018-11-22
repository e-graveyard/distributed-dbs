package client;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.util.Random;

class Client
{
    private String name;
    private Random rand;
    private int controllerPort;

    public Client(int controllerPort)
    {
        this.rand = new Random();
        this.name = this.generateUniqueName();
        this.controllerPort = controllerPort;
    }

    private String generateUniqueName()
    {
        String[] humanNames = {
            "JAMES", "JOHN", "ROBERT", "MICHAEL", "WILLIAM",
            "DAVID", "RICHARD", "CHARLES", "JOSEPH", "THOMAS",
            "MARY", "LINDA", "ELIZABETH", "SUSAN", "MARGARETH",
            "DOROTHY", "LISA", "NANCY", "DONNA", "MICHELLE"
        };

        int index = rand.nextInt(humanNames.length);

        String name = humanNames[index];
        String numb = Integer.toString(rand.nextInt(1000000));

        return (name + "-" + numb);
    }

    public String getName()
    {
        return name;
    }

    public int getControllerPort()
    {
        return controllerPort;
    }

    public void act()
    {
        // ...
        int option;

        // ...
        String title, author, publication, isbn, pages;

        // ...
        String[] bookInfo;

        // ...
        Parser parsedResponse;

        // ...
        Requisitor requisitor = new Requisitor(this);

        boolean persist = true;
        while(persist)
        {
            Interface.drawMenu();

            option = Interface.getOption();
            System.out.print("\n");

            if (option < 0 || option > 4)
            {
                Logger.error("Invalid option.");
                continue;
            }

            switch(option)
            {
                case 0:
                    Logger.info("Bye-Bye!");
                    persist = false;
                    break;

                case 1:
                    Logger.info("Input the book information:\n");

                    bookInfo = Interface.getBookInformation();
                    if(bookInfo == null)
                    {
                        System.out.print("\n");
                        Logger.error("Invalid or empty input. Unable to create.");
                        continue;
                    }

                    isbn = Interface.getIsbn();
                    if(isbn == null)
                    {
                        System.out.print("\n");
                        Logger.error("Invalid ISBN identifier. Unable to create.");
                        continue;
                    }

                    title       = bookInfo[0];
                    author      = bookInfo[1];
                    publication = bookInfo[2];
                    pages       = bookInfo[3];

                    System.out.print("\n");

                    parsedResponse = requisitor.createBook(title, author, publication, isbn, pages);
                    if(parsedResponse.isOkay())
                    {
                        Logger.success("Book registered by *purple" + parsedResponse.getSender() + "*normal.");
                    }
                    else
                    {
                        Logger.error("Could not register.");
                    }
                    break;

                case 2:
                    Logger.info("Enter the book's ISBN to read:\n");

                    isbn = Interface.getIsbn();
                    System.out.print("\n");
                    if(isbn == null)
                    {
                        Logger.error("Invalid ISBN identifier. Unable to read.");
                        continue;
                    }

                    parsedResponse = requisitor.readBook(isbn);
                    if(parsedResponse.isOkay())
                    {
                        Logger.success("Book info readed by *purple" + parsedResponse.getSender() + "*normal.");
                        Interface.drawBookInformation(parsedResponse.toBook());
                    }
                    else
                    {
                        Logger.error("Could not read.");
                    }
                    break;

                case 3:
                    Logger.info("Enter the book's ISBN to update:\n");

                    isbn = Interface.getIsbn();
                    if(isbn == null)
                    {
                        System.out.print("\n");
                        Logger.error("Invalid ISBN identifier. Unable to update.");
                        continue;
                    }

                    System.out.print("\n");
                    Logger.info("Input the book information:\n");

                    bookInfo = Interface.getBookInformation();
                    if(bookInfo == null)
                    {
                        System.out.print("\n");
                        Logger.error("Invalid or empty input. Unable to update.");
                        continue;
                    }

                    title       = bookInfo[0];
                    author      = bookInfo[1];
                    publication = bookInfo[2];
                    pages       = bookInfo[3];

                    System.out.print("\n");

                    parsedResponse = requisitor.updateBook(title, author, publication, isbn, pages);
                    if(parsedResponse.isOkay())
                    {
                        Logger.success("Book info updated by *purple" + parsedResponse.getSender() + "*normal.");
                    }
                    else
                    {
                        Logger.error("Could not update.");
                    }
                    break;

                case 4:
                    Logger.info("Enter the book's ISBN to delete:\n");

                    isbn = Interface.getIsbn();
                    System.out.print("\n");
                    if(isbn == null)
                    {
                        Logger.error("Invalid ISBN identifier. Unable to delete.");
                        continue;
                    }

                    parsedResponse = requisitor.deleteBook(isbn);
                    if(parsedResponse.isOkay())
                    {
                        Logger.success("Book deleted by *purple" + parsedResponse.getSender() + "*normal.");
                    }
                    else
                    {
                        Logger.error("Could not delete.");
                    }
                    break;
            }
        }
    }
}
