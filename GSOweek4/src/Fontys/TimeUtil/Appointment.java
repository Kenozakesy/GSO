package Fontys.TimeUtil;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Gebruiker on 27-9-2017.
 */
public class Appointment {

    private String subject;

    private ITimeSpan timeSpan;

    private ArrayList<Contact> contacts;

    public Appointment(String subject, ITimeSpan timeSpan) throws Exception{
            this.subject = subject;
            this.timeSpan = timeSpan;
            this.contacts = new ArrayList<>();
    }

    public String getSubject(){
        return subject;
    }

    public ITimeSpan getTimeSpan(){
        return timeSpan;
    }

    public Iterator<Contact> invitees(){
        return contacts.iterator();
    }

    //add contatc but only if contact doesn't already exist
    public boolean addContact(Contact c){
        Iterator<Contact> invitees = invitees();
        while(invitees.hasNext()){
            if (c == invitees.next())
            {
                return false;
            }
        }

        this.contacts.add(c);
        try {
            c.addAppointment(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    //removes contact
    public void removeContact(Contact c){
        Iterator<Contact> invitees = invitees();
        while(invitees.hasNext()){
            if (c == invitees.next())
            {
                this.contacts.remove(c);
//                for (Contact con : this.contacts){
//                    if (c == con)
//                    {
//                        this.contacts.remove(con);
//                    }
//                }
            }

        }
    }
}
