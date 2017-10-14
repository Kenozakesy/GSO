package sample.ClientServer;

import sample.Classes.MockEffectChange;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

/**
 * Created by Gebruiker on 11-10-2017.
 */
public class RMIServer {

    private boolean createRegistry = true;

    private static final int portNumber = 1099;
    private static final String bindingName = "exchange";
    private Registry registry = null;
    private MockEffectChange MockExchange = null;


    //Constructor
    public RMIServer() {

        // Print port number for registry
        System.out.println("Server: Port number " + portNumber);

        // Create student administration
        try {
            MockExchange = new MockEffectChange();
            System.out.println("Server: MockExchange created");
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create MockExchange");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            MockExchange = null;
        }

        // Create registry at port number
        try {
            registry = LocateRegistry.createRegistry(portNumber);
            System.out.println("Server: Registry created on port number " + portNumber);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot create registry");
            System.out.println("Server: RemoteException: " + ex.getMessage());
            registry = null;
        }

        // Bind MockExchange using registry
        try {
            registry.rebind(bindingName, MockExchange);
        } catch (RemoteException ex) {
            System.out.println("Server: Cannot bind MockExchange");
            System.out.println("Server: RemoteException: " + ex.getMessage());
        }
    }

    //Methods
    // Print IP addresses and network interfaces
    private static void printIPAddresses() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            System.out.println("Server: IP Address: " + localhost.getHostAddress());
            // Just in case this host has multiple IP addresses....
            InetAddress[] allMyIps = InetAddress.getAllByName(localhost.getCanonicalHostName());
            if (allMyIps != null && allMyIps.length > 1) {
                System.out.println("Server: Full list of IP addresses:");
                for (InetAddress allMyIp : allMyIps) {
                    System.out.println("    " + allMyIp);
                }
            }
        } catch (Exception ex) {
            System.out.println("Server: Cannot get IP address of local host");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }

        try {
            System.out.println("Server: Full list of network interfaces:");
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                System.out.println("    " + intf.getName() + " " + intf.getDisplayName());
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    System.out.println("        " + enumIpAddr.nextElement().toString());
                }
            }
        } catch (SocketException ex) {
            System.out.println("Server: Cannot retrieve network interface list");
            System.out.println("Server: UnknownHostException: " + ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // Welcome message
        System.out.println("SERVER USING REGISTRY");

        // Print IP addresses and network interfaces
        printIPAddresses();

        // Create server
        RMIServer server = new RMIServer();
    }
}
