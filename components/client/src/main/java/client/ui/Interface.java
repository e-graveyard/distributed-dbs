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

    public static String getIsbn()
    {
        String isbn;

        try
        {
            System.out.print("\tISBN: ");
            isbn = (new Scanner(System.in)).nextLine();

            if(Input.isEmpty(isbn) || !Input.isIsbn(isbn))
                throw new RuntimeException();

            return isbn;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public static String[] getBookInformation()
    {
        String title, author, publication, pages;
        Scanner scan = new Scanner(System.in);

        try
        {
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

            System.out.print("\tPages: ");
            pages = scan.nextLine();
            if(Input.isEmpty(pages))
                throw new RuntimeException();

            Integer.parseInt(pages);
            String[] information = { title, author, publication, pages };

            return information;
        }
        catch(Exception e)
        {
            return null;
        }
    }
}
