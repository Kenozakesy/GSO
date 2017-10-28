package sample.Interfaces;

import sample.FontysPublisher.IRemotePropertyListener;
import sample.FontysPublisher.IRemotePublisherForListener;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public interface IEffectsExchange extends Remote,IRemotePublisherForListener {

    List<IFunds> GetRates() throws RemoteException;

}
