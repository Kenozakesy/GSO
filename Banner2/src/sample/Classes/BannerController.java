package sample.Classes;

import javafx.application.Platform;
import sample.ClientServer.RMIClient;
import sample.FontysPublisher.IRemotePropertyListener;
import sample.Interfaces.IEffectsExchange;
import sample.Interfaces.IFunds;

import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener
{
    private transient AEXBanner banner;
    private transient IEffectsExchange effectsExchange;
    private transient Timer pollingTimer;
    private transient RMIClient client;
    private transient UpdateTask task;

    private static String iPadress = "localhost";


    public BannerController(AEXBanner banner) throws RemoteException, NotBoundException {

        this.banner = banner;


        client = new RMIClient(iPadress,1099,this);

        // Start polling timer: update banner every two seconds
        // Pull method
        //    task = new UpdateTask(banner, effectsExchange);
    //    pollingTimer = new Timer();
     //   pollingTimer.schedule(task, 0, 2000);

    }

    // Stop banner controller
    public void stop() throws RemoteException{
        effectsExchange.unsubscribeRemoteListener(this,"mock");

     //   pollingTimer.cancel();
      //  ((MockEffectChange)effectsExchange).cancelTimer();
         // Stop simulation timer of effectenbeurs

    }
    public void setEffectsExchange(IEffectsExchange effectsExchange){
        this.effectsExchange = effectsExchange;

    }
    public void setRates(List<IFunds> fundsList){
        final StringBuilder b = new StringBuilder();
        if(fundsList != null) {
            try {
                for (IFunds f : fundsList) {
                    b.append(f.GetName() + " " + " " + f.GetRate() + " ");
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            banner.setExchange(b.toString().trim());
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) throws RemoteException {
        setRates((ArrayList<IFunds>)evt.getNewValue());
    }

    public void subscribe() throws RemoteException{
        effectsExchange.subscribeRemoteListener(this,"mock");
    }

}
