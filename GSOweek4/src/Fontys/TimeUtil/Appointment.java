package Fontys.TimeUtil;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 27-9-2017.
 */
public class Appointment {

    private String subject;

    private ITimeSpan timeSpan;

    private ArrayList<Contact> contacts;

    public Appointment(String subject, ITimeSpan timeSpan) throws Exception{
        if (!subject.isEmpty() || timeSpan != null)
        {
            this.subject = subject;
            this.timeSpan = timeSpan;
            this.contacts = new ArrayList<>();
        }
        else{
            throw new Exception("Subject or timespan cant be empty or null");
        }
    }

    public String getSubject(){
        return subject;
    }


    public ITimeSpan getTimeSpan(){
        return timeSpan;
    }

    public ArrayList<Contact> invitees(){
        return contacts;
    }

    //add contatc but only if contact doesn't already exist
    public boolean addContact(Contact contact){

        boolean result = false;
        if (contact != null)
        {
            //still needs work
            if(!contacts.contains(contact))
            {
                this.contacts.add(contact);
                result = true;
            }
        }
        return result;
    }

    //removes contact
    public boolean removeContact(Contact contact){
        boolean result = false;

        if (contact != null)
        {
            if(contacts.contains(contact))
            {
                this.contacts.remove(contact);
                result = true;
            }
        }

        return result;
    }
}
