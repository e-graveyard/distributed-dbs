package client;

import java.util.Scanner;
import java.lang.RuntimeException;

class Interface
{
    public static void drawMenu()
    {
        Logger.info("Choose an option:\n");
        System.out.println("\t[1] Create\n" +
                "\t[2] Read\n" +
                "\t[3] Update\n" +
                "\t[4] Delete\n" +
                "\t[0] Exit\n");

        System.out.print("> ");
    }

    public static int getOption()
    {
        int option = 999;
        String value = (new Scanner(System.in)).nextLine();

        if(Input.isInteger(value))
            option = Integer.parseInt(value);

        return option;
    }

    public static Book getBook()
    {
        String title, author, publication, isbn, pages;

        Logger.info("Input the book information:\n");

        try
        {
            Scanner scan = new Scanner(System.in);

            System.out.print("\tTitle: ");
            title = scan.nextLine();
            if(Input.isEmpty(title))
                throw new RuntimeException();

            System.out.print("\tAuthor: ");
            author = scan.nextLine();
            if(Input.isEmpty(author))
                throw new RuntimeException();

            System.out.print("\tPublication: ");
            publication = scan.nextLine();
            if(Input.isEmpty(publication))
                throw new RuntimeException();

            System.out.print("\tISBN: ");
            isbn = scan.nextLine();
            if(Input.isEmpty(isbn))
                throw new RuntimeException();

            System.out.print("\tPages: ");
            pages = scan.nextLine();
            if(Input.isEmpty(pages))
                throw new RuntimeException();

            int p = Integer.parseInt(pages);

            System.out.print("\n");

            return new Book(title, author, publication, isbn, p);
        }
        catch(NumberFormatException e)
        {
            System.out.print("\n");
            Logger.error("Invalid input.");

            return null;
        }
        catch(RuntimeException e)
        {
            System.out.print("\n");
            Logger.error("Empty input.");

            return null;
        }
    }
}
