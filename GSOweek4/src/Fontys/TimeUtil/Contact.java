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
        if(name.isEmpty() || name == null){
            try {
                throw new Exception("name cannot be empty or null");
            } catch (Exception e) {
                e.toString();
            }
        }

        this.name = name;
        this.appointments = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    boolean addAppointment(Appointment a) throws Exception{
        boolean result = true;

        for(Appointment ap : appointments){

            if(a.getTimeSpan().getBeginTime().compareTo(ap.getTimeSpan().getBeginTime()) >= 0 && a.getTimeSpan().getBeginTime().compareTo(ap.getTimeSpan().getEndTime()) <= 0)
            {
                return false;
            }
            else if(a.getTimeSpan().getEndTime().compareTo(ap.getTimeSpan().getBeginTime()) >= 0 && a.getTimeSpan().getEndTime().compareTo(ap.getTimeSpan().getEndTime()) <= 0)
            {
                return false;
            }
            else if(a.getTimeSpan().getBeginTime().compareTo(ap.getTimeSpan().getBeginTime()) == 0 && a.getTimeSpan().getEndTime().compareTo(ap.getTimeSpan().getEndTime()) == 0)
            {
                return false;
            }
            else if(a.getTimeSpan().getBeginTime().compareTo(ap.getTimeSpan().getBeginTime()) < 0 && a.getTimeSpan().getEndTime().compareTo(ap.getTimeSpan().getEndTime()) < 0)
            {
                return false;
            }
            else if(a.getTimeSpan().getBeginTime().compareTo(ap.getTimeSpan().getBeginTime()) >= 0 && a.getTimeSpan().getEndTime().compareTo(ap.getTimeSpan().getEndTime()) >= 0)
            {
                return false;
            }
            else if(a.getTimeSpan().isPartOf(ap.getTimeSpan()) || ap.getTimeSpan().isPartOf(a.getTimeSpan()))
            {
                return false;
            }
        }
        this.appointments.add(a);
        return true;
    }

    boolean removeAppointment(Appointment a){
        boolean result = false;

        if(this.appointments.contains(a)){
            this.appointments.remove(a);
            result = true;
        }
        return result;
    }

    public Iterator<Appointment> appointments(){
        return appointments.iterator();
    }
}
