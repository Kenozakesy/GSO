package sample.Classes;

import javafx.application.Platform;
import sample.Interfaces.IEffectsExchange;
import sample.Interfaces.IFunds;

import java.rmi.RemoteException;
import java.util.List;
import java.util.TimerTask;

/**
 * Created by Jordi on 7-10-2017.
 */
public class UpdateTask extends TimerTask {
    AEXBanner banner;
    IEffectsExchange effectsExchange;

    public UpdateTask(AEXBanner banner, IEffectsExchange effectsExchange) {
        this.banner = banner;
        this.effectsExchange = effectsExchange;

    }

    @Override
    public void run() {
        final StringBuilder b = new StringBuilder();

        try {
            for(IFunds f : effectsExchange.GetRates())
            {
                b.append(f.GetName() + " " + " " + f.GetRate() + " ");
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    banner.setExchange(b.toString().trim());
                }
            });
    }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }}
