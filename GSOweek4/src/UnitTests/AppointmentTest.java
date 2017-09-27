package UnitTests;

import Fontys.TimeUtil.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Gebruiker on 27-9-2017.
 */
public class AppointmentTest {

    @Test
    public void ConstructorTest()
    {
        ITime begin = new Time(2017, 9, 27, 12, 30);
        ITime end = new Time(2017, 9, 27,14,30);

        ITimeSpan span = new TimeSpan(begin, end);

        String sub = "test";
        Appointment pon = new Appointment(sub, span);

        Assert.assertEquals(begin, pon.getTimeSpan().getBeginTime());
        Assert.assertEquals(end, pon.getTimeSpan().getEndTime());


        Assert.assertEquals(sub, pon.getSubject());

        assertFalse(pon.invitees().hasNext());
    }

    @Test
    public void AddAppointmentTest() {
        ITime begin = new Time(2017, 9, 27, 12, 30);
        ITime end = new Time(2017, 9, 27, 14, 30);

        ITimeSpan span = new TimeSpan(begin, end);

        String sub = "test";
        Appointment pon = new Appointment(sub, span);

    }

    @Test
    public void RemoveAppointmentTest() {
        ITime begin = new Time(2017, 9, 27, 12, 30);
        ITime end = new Time(2017, 9, 27, 14, 30);

        ITimeSpan span = new TimeSpan(begin, end);

        String sub = "test";
        Appointment pon = new Appointment(sub, span);

    }
}
