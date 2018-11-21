package client;

class Input
{
    public static boolean isInteger(String value)
    {
        try
        {
            Integer.parseInt(value);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    public static boolean isArgValid(String[] args)
    {
        if(args.length < 1)
        {
            Logger.error("No server port were provided.");
            return false;
        }

        for(String arg : args)
        {
            if(!isInteger(arg))
            {
                Logger.error("Value '@value' is not an integer type.".replace("@value", arg));
                return false;
            }

            int port = Integer.parseInt(arg);
            if(port < 5000 || port > 65535)
            {
                Logger.error("Provided port '@port' not in range.".replace("@port", Integer.toString(port)));
                return false;
            }
        }

        return true;
    }

    public static int[] strListToIntList(String[] strList)
    {
        int[] intList = new int[strList.length];

        for(int i = 0; i < strList.length; i++)
            intList[i] = Integer.parseInt(strList[i]);

        return intList;
    }
}
