package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

import java.lang.InterruptedException;

class Retry implements Runnable
{
    private Router router;
    private int clock;

    public Retry(Router router, int clock)
    {
        this.router = router;
        this.clock = clock;
    }

    public void run()
    {
        while(true)
        {
            try
            {
                Thread.sleep(clock * 1000);
            }
            catch(InterruptedException e)
            {
                break;
            }

            Logger.info("Checking servers availability");
            router.checkServersAvailability();
            Logger.info("Cheking again in @secs seconds.".replace("@secs", Integer.toString(clock)));
        }
    }
}
