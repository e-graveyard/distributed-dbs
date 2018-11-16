package client;

import java.util.Scanner;

class Interface
{
    public static void drawMenu()
    {
        System.out.println("Choose an option:\n\n" +
                "[1] Create\n" +
                "[2] Read\n" +
                "[3] Update\n" +
                "[4] Delete\n" +
                "[0] Exit\n");
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
