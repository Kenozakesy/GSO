package UnitTests;

import Fontys.TimeUtil.DayInWeek;
import Fontys.TimeUtil.ITime;
import Fontys.TimeUtil.Time;
import Fontys.TimeUtil.TimeSpan;
import org.junit.Assert;
import org.junit.Test;

import java.security.Timestamp;

/**
 * Created by Gebruiker on 20-9-2017.
 */
public class TimeSpanTest
{
    @Test
    public void TimeSpanTest()
    {
        int year = 2015;
        int month = 12;
        int day = 12;
        int hour = 2;
        int minutes = 34;

        ITime time1 = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year,month,day,hour,minutes);

        TimeSpan span = new TimeSpan(time1, time2);

        Assert.assertEquals(time1, span.getBeginTime());
        Assert.assertEquals(time2, span.getEndTime());
    }




}
