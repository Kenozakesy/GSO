package Fontys.TimeUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Gebruiker on 27-9-2017.
 */
public class Contact {


    private String name;
    private List<Appointment> appointments;

    public Contact(String name){
        this.name = name;
        this.appointments = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    boolean addAppointment(Appointment a) {
        Iterator<Appointment> appointments = appointments();
        while (appointments.hasNext()) {
            if (a == appointments.next()) {
                return false;
            }
        }

        this.appointments.add(a);
        a.addContact(this);
        return true;
    }

    void removeAppointment(Appointment a){
        Iterator<Appointment> appointments = appointments();
        while (appointments.hasNext()) {
            if (a == appointments.next()) {
                this.appointments.remove(a);
            }
        }
    }

    public Iterator<Appointment> appointments(){
        return appointments.iterator();
    }
}
