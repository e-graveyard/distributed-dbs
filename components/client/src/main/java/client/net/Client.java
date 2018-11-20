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

        int index = this.rand.nextInt(humanNames.length);

        String name = humanNames[index];
        String numb = Integer.toString(this.rand.nextInt(1000000));

        return (name + "-" + numb);
    }

    public String getName()
    {
        return this.name;
    }

    public int getControllerPort()
    {
        return this.controllerPort;
    }

    public void act()
    {
        // ...
        Book book;

        // ...
        int option;

        // ...
        String isbn;

        // ...
        String[] bookInfo;

        // ...
        Parser parser;

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

                    System.out.print("\n");

                    String title    = bookInfo[0],
                        author      = bookInfo[1],
                        publication = bookInfo[2];
                    int pages       = Integer.parseInt(bookInfo[3]);

                    book = new Book(title, author, publication, isbn, pages);
                    Logger.info(book.getTitle());

                    break;

                case 2:
                    Logger.info("Enter the book's ISBN to search:\n");

                    isbn = Interface.getIsbn();
                    if(isbn == null)
                    {
                        System.out.println("\n");
                        Logger.error("Invalid ISBN identifier. Unable to create.");
                        continue;
                    }

                    book = requisitor.readBook(isbn);
                    if(book != null)
                    {
                        // Interface.drawBookInformation(book);
                    }
                    else
                    {
                        // ...
                    }

                    break;

                case 3:
                    break;

                case 4:
                    break;
            }
        }
    }
}
