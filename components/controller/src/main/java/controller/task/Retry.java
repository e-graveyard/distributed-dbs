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

    public Retry(Router router, int secondsUntilChecking)
    {
        this.router = router;
        this.clock  = secondsUntilChecking * 1000;
    }

    public void run()
    {
        boolean active = true;
        try
        {
            while(active)
            {
                Thread.sleep(clock);
                router.checkServersAvailability();
            }
        }
        catch(InterruptedException e)
        {
            active = false;
        }
    }
}
