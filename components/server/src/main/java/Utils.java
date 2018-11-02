package main;

import java.util.Random;

public class Utils
{
    private static Random rand = new Random();

    private static final int MAX_PORT_NUMBER = 65535;
    private static final int MIN_PORT_NUMBER = 5000;

    public static String generateUniqueName()
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

        String uniqueName = name + "-" + numb;

        return (name + "-" + numb);
    }

    public static int generateValidPort()
    {
        int port = rand.nextInt((MAX_PORT_NUMBER - MIN_PORT_NUMBER) + 1) - MIN_PORT_NUMBER;

        return port;
    }
}
