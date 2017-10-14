package sample.Classes;

import sample.Interfaces.IEffectsExchange;

import java.rmi.RemoteException;
import java.util.Timer;

public class BannerController
{
    private AEXBanner banner;
    private IEffectsExchange effectsExchange;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner) {

        this.banner = banner;
        try {
            this.effectsExchange = new MockEffectChange();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        pollingTimer.schedule(new UpdateTask(this.banner, this.effectsExchange), 0, 2000);
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        ((MockEffectChange)effectsExchange).cancelTimer();
         // Stop simulation timer of effectenbeurs

    }

}
