package sample.Classes;

import sample.Interfaces.IEffectsExchange;
import java.util.Timer;

public class BannerController
{
    private AEXBanner banner;
    private IEffectsExchange effectExChange;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner) {

        this.banner = banner;
        this.effectExChange = new MockEffectChange();

        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();

        // TODO get exchange information
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }




}
