package sample.Interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public interface IEffectsExchange extends Remote {

    List<IFunds> GetRates() throws RemoteException;
}
