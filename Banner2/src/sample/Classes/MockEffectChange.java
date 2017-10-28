package sample.Classes;

import sample.FontysPublisher.IRemotePropertyListener;
import sample.FontysPublisher.IRemotePublisherForListener;
import sample.FontysPublisher.Publisher;
import sample.FontysPublisher.RemotePublisher;
import sample.Interfaces.IEffectsExchange;
import sample.Interfaces.IFunds;

import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public class MockEffectChange extends UnicastRemoteObject implements IEffectsExchange, IRemotePublisherForListener, Serializable {

    private List<IFunds> funds;
    private final Timer rateTimer;
    private Random randomRates = new Random();
    TimerTask task;
    RemotePublisher publisher;

    public MockEffectChange() throws RemoteException {

        funds = new ArrayList<>();

        funds.add(new Fund("Google", 35));
        funds.add(new Fund("Apple", 6));
        funds.add(new Fund("Windows", 66));
        funds.add(new Fund("De turk", 900));

        String[] properties = new String[1];
        properties[0] = "mock";
        publisher = new RemotePublisher(properties);

        //aanmaken timer
        rateTimer = new Timer();
        start();

    }

    private void start() throws RemoteException {
         task = new TimerTask() {
            @Override
            public void run() {
                for (IFunds fund : funds) {
                    double Rates = Math.round(randomRates.nextDouble() * 150.0) - Math.round(randomRates.nextDouble() * 25.0);
                    ((Fund) fund).setRate(Rates);
                }
                try {
                    publisher.inform("mock", null, funds);
                }
                catch (RemoteException e) {}
            }
            };

        rateTimer.scheduleAtFixedRate(task, 0, 3000);

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

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.subscribeRemoteListener(listener, property);
    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {
        publisher.unsubscribeRemoteListener(listener,property);
    }

}
