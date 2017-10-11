package sample.Classes;

import sample.Interfaces.IEffectsExchange;
import sample.Interfaces.IFunds;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public class MockEffectChange extends UnicastRemoteObject implements IEffectsExchange {

    private List<IFunds> funds;
    private final Timer rateTimer;
    private Random randomRates = new Random();
    TimerTask task;


    public MockEffectChange() throws RemoteException {

        funds = new ArrayList<>();

        funds.add(new Fund("Google", 35));
        funds.add(new Fund("Apple", 6));
        funds.add(new Fund("Windows", 66));
        funds.add(new Fund("De turk", 900));

        //aanmaken timer
        rateTimer = new Timer();
        start();

    }

    private void start() {
         task = new TimerTask() {
            @Override
            public void run() {
                for (IFunds fund : funds) {
                    double Rates = Math.round(randomRates.nextDouble() * 150.0) - Math.round(randomRates.nextDouble() * 25.0);
                    ((Fund) fund).setRate(Rates);
                }
            }
            };
        startTimer();

    }


    public void startTimer(){
        rateTimer.scheduleAtFixedRate(task, 0, 5000);
    }

    public List<IFunds> GetRates()
    {
        return funds;
    }

    public void cancelTimer(){
        rateTimer.cancel();
    }

    public Iterator<IFunds> getIterator()
    {
        return funds.iterator();
    }
}
