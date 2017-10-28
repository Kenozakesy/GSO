package sample.Classes;

import sample.FontysPublisher.IRemotePropertyListener;
import sample.Interfaces.IEffectsExchange;
import sample.Interfaces.IFunds;

import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public class EffectExchange implements IEffectsExchange {


    @Override
    public List<IFunds> GetRates() {
        return null;
    }

    @Override
    public void subscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {

    }

    @Override
    public void unsubscribeRemoteListener(IRemotePropertyListener listener, String property) throws RemoteException {

    }
}
