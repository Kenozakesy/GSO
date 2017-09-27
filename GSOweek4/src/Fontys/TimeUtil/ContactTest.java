package Fontys.TimeUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Gebruiker on 27-9-2017.
 */
public class ContactTest {

    @Test
    public void addContactTest()
    {
        String persoon = "TestPersoon";
        Contact contact = new Contact(persoon);
        ITime begin = new Time(2017, 9, 27, 12, 30);
        ITime end = new Time(2017, 9, 27, 14, 30);

        ITimeSpan span = new TimeSpan(begin, end);

        String sub = "test";
        Appointment pon = new Appointment(sub, span);

        Assert.assertEquals(persoon, contact.getName());
        Assert.assertTrue(contact.addAppointment(pon));
        Assert.assertFalse(contact.addAppointment(pon));

    }

    @Test
    public void removeContactTest()
    {
        ITime begin = new Time(2017, 9, 27, 12, 30);
        ITime end = new Time(2017, 9, 27, 14, 30);

        ITimeSpan span = new TimeSpan(begin, end);

        String sub = "test";
        Appointment pon = new Appointment(sub, span);

        String persoon = "TestPersoon";
        Contact contact = new Contact(persoon);

        contact.addAppointment(pon);
        contact.removeAppointment(pon);

        while (contact.appointments().hasNext()){
            if (pon == contact.appointments().next()) {
               Assert.assertFalse("Appointment is still present", false);
            }

        }
        Assert.assertTrue("Appointment is no longer present", true);
    }
}
