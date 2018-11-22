package controller;
/*
   Caian R. Ertl     (@caianrais)       - 20733152
   Diogo Casagrande  (@DiogoCasagrande) - 20718678
   Julia G. C. Chiba (@JuliaChiba)      - 20511823
 */

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
        while(true)
        {
            Thread.sleep(clock);
            router.checkServersAvailability();
        }
    }
}
