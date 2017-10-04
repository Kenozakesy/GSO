package sample.Classes;

import sample.Interfaces.IEffectsExchange;
import sample.Interfaces.IFunds;

import java.util.*;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public class MockEffectChange implements IEffectsExchange {

    private List<IFunds> funds;
    private final Timer rateTimer;
    private Random randomRates = new Random();

    public MockEffectChange()
    {
        funds = new ArrayList<>();

        funds.add(new Fund("Google", 35));
        funds.add(new Fund("Apple", 6));
        funds.add(new Fund("Windows", 66));
        funds.add(new Fund("De turk", 900));

        //aanmaken timer
        rateTimer = new Timer();

        //in dit stuk code word elke 5 seconde een nieuwe koers gegenereerd en geset
        rateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (IFunds fonds : funds) {
                    Fund fondsToUpdate = (Fund) fonds; //has to be changed
                    double Rates = Math.round(randomRates.nextDouble() * 150.0) - Math.round(randomRates.nextDouble() * 25.0);
                    fondsToUpdate.setRate(Rates);
                }
            }
        }, 0, 5000);
    }

    @Override
    public List<IFunds> GetRates()
    {
        return funds;
    }
}
