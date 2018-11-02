package main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{
    public static final String COLOR_RESET       = "\u001B[0m";
    public static final String COLOR_CYAN        = "\u001B[36m";
    public static final String COLOR_WHITE_BOLD  = "\033[1;37m";
    public static final String COLOR_RED_BOLD    = "\033[1;31m";
    public static final String COLOR_YELLOW_BOLD = "\033[1;33m";

    private static String getCurrentDateTime()
    {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        return d.format(now);
    }

    private static String pretify(String message)
    {
        return message
            .replace("*normal",      COLOR_RESET)
            .replace("*cyan",        COLOR_CYAN)
            .replace("*bold",        COLOR_WHITE_BOLD)
            .replace("*red_bold",    COLOR_RED_BOLD)
            .replace("*yellow_bold", COLOR_YELLOW_BOLD);
    }

    public static void info(String message)
    {
        String info = pretify("[*cyan@datetime*normal] *boldINFO:*normal @message");
        info = info
            .replace("@datetime", getCurrentDateTime())
            .replace("@message", message);

        System.out.println(info);
    }
}
