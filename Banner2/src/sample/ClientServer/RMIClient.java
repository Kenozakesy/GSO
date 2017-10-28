package sample.ClientServer;

import sample.Classes.BannerController;
import sample.Classes.Fund;
import sample.FontysPublisher.RemotePublisher;
import sample.Interfaces.IEffectsExchange;
import sample.Interfaces.IFunds;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.*;

/**
 * Created by Gebruiker on 11-10-2017.
 */

//RMI client to connect to the server
public class RMIClient {

    private static final String bindingName = "exchange";
    private Timer pollingTimer;

    private Registry registry = null;
    private BannerController bannerController;
    private IEffectsExchange effectexchange = null;
    private RemotePublisher publisher;

    // Constructor
    public RMIClient(String ipAddress, int portNumber, BannerController bannerController) throws RemoteException,NotBoundException {

       this.bannerController = bannerController;

        // Print IP address and port number for registry
        System.out.println("Client: IP Address: " + ipAddress);
        System.out.println("Client: Port number " + portNumber);

        // Locate registry at IP address and port number
        try {
            registry = LocateRegistry.getRegistry(ipAddress, portNumber);
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }

            // Print result locating registry
            if (registry != null) {
                effectexchange = (IEffectsExchange)registry.lookup(bindingName);
                bannerController.setEffectsExchange(effectexchange);
                bannerController.subscribe();

                System.out.println("Client: Registry located");
            } else {
                System.out.println("Client: Cannot locate registry");
                System.out.println("Client: Registry is null pointer");
            }

        // Print contents of registry
        if (registry != null) {
            printContentsRegistry();
        }

        pollingTimer = new Timer();
        pollingTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Bind student administration using registry
                if (registry != null) {
                    try {
                        effectexchange = (IEffectsExchange) registry.lookup(bindingName);
                    } catch (RemoteException ex) {
                        System.out.println("Client: Cannot bind MockExchange");
                        System.out.println("Client: RemoteException: " + ex.getMessage());
                        effectexchange = null;
                    } catch (NotBoundException ex) {
                        System.out.println("Client: Cannot bind MockExchange");
                        System.out.println("Client: NotBoundException: " + ex.getMessage());
                        effectexchange = null;
                    }
                }

                // Print result binding student administration
                if (effectexchange != null) {
                    System.out.println("Client: Student administration bound");
                } else {
                    System.out.println("Client: Student administration is null pointer");
                }

                // Test RMI connection
                if (effectexchange != null) {
                    testPullBanner();
                }
            }
        }, 0, 3000);

    }

    // Print contents of registry
    private void printContentsRegistry() {
        try {
            String[] listOfNames = registry.list();
            System.out.println("Client: list of names bound in registry:");
            if (listOfNames.length != 0) {
                for (String s : registry.list()) {
                    System.out.println(s);
                }
            } else {
                System.out.println("Client: list of names bound in registry is empty");
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot show list of names bound in registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
    }

    // Test RMI connection (needs to be changed)
    private void testPullBanner() {
        // Get number of students
        try {
            List<IFunds> funds = effectexchange.GetRates();
            for (IFunds f: funds) {
                Fund fund = (Fund) f;
                System.out.println(fund.GetName() + " " + fund.GetRate());
            }
        } catch (RemoteException ex) {
            System.out.println("Client: Cannot get number of students");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }

//        // Add student Jan to student administration
//        try {
//            Student jan = studentAdmin.addStudent("Jan", "Jansen", 1995);
//            System.out.println("Client: Student " + jan.toString() + " added to student administration");
//        } catch (RemoteException ex) {
//            System.out.println("Client: Cannot add first student to student administration");
//            System.out.println("Client: RemoteException: " + ex.getMessage());
//        }
    }

    // Main method
 //   public static void main(String[] args) {

        // Welcome message
  ////      System.out.println("CLIENT USING REGISTRY");

        // Get ip address of server
 //       Scanner input = new Scanner(System.in);
 //       System.out.print("Client: Enter IP address of server: ");
 //       String ipAddress = input.nextLine();

        // Get port number
 //       System.out.print("Client: Enter port number: ");
 //       int portNumber = input.nextInt();


        // Create client
 //       RMIClient client = new RMIClient(ipAddress, portNumber, bannerController);
    }

//}
