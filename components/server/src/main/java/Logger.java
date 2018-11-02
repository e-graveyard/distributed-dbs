package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{
    public static final String COLOR_RESET       = "\u001B[0m";
    public static final String COLOR_CYAN        = "\u001B[36m";
    public static final String COLOR_PURPLE      = "\033[0;35m";
    public static final String COLOR_WHITE_BOLD  = "\033[1;37m";
    public static final String COLOR_RED_BOLD    = "\033[1;31m";
    public static final String COLOR_YELLOW_BOLD = "\033[1;33m";
    public static final String COLOR_GREEN_BOLD  = "\033[1;32m";

    private static String currentDateTime()
    {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return d.format(now);
    }

    private static String prettify(String message)
    {
        return message
            .replace("*normal",      COLOR_RESET)
            .replace("*cyan",        COLOR_CYAN)
            .replace("*purple",      COLOR_PURPLE)
            .replace("*bold",        COLOR_WHITE_BOLD)
            .replace("*red_bold",    COLOR_RED_BOLD)
            .replace("*yellow_bold", COLOR_YELLOW_BOLD)
            .replace("*green_bold",  COLOR_GREEN_BOLD);
    }

    private static String compose(String type, String message)
    {
        String datef = "[*cyan" + currentDateTime() + "*normal]";
        String typef = null;

        switch(type)
        {
            case "info":
                typef = "*boldINFO:*normal";
                break;

            case "error":
                typef = "*red_boldERROR:*normal";
                break;

            case "warning":
                typef = "*yellow_boldWARNING:*normal";
                break;

            case "success":
                typef = "*green_boldSUCCESS:*normal";
                break;
        }

        String composition = "@datef @typef @message"
            .replace("@datef", datef)
            .replace("@typef", typef)
            .replace("@message", message);

        return prettify(composition);
    }

    public static void info(String message)
    {
        System.out.println(compose("info", message));
    }

    public static void error(String message)
    {
        System.out.println(compose("error", message));
    }

    public static void warning(String message)
    {
        System.out.println(compose("warning", message));
    }

    public static void success(String message)
    {
        System.out.println(compose("success", message));
    }
}
