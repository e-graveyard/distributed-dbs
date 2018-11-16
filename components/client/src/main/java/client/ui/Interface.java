package client;

import java.util.Scanner;

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
}
