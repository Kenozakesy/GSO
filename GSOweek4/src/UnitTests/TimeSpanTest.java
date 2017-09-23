package UnitTests;

import Fontys.TimeUtil.*;
import org.junit.Assert;
import org.junit.Test;

import java.security.Timestamp;

/**
 * Created by Gebruiker on 20-9-2017.
 */
public class TimeSpanTest
{

    //Test that work
    @Test
    public void TimeSpanTest()
    {
        int year = 2017;
        int month = 12;
        int day = 11;
        int hour = 2;
        int minutes = 34;

        int year2 = 2017;
        int month2 = 12;
        int day2 = 12;
        int hour2 = 2;
        int minutes2 = 34;

        ITime time1 = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year2,month2,day2,hour2,minutes2);

        TimeSpan span = new TimeSpan(time1, time2);

        Assert.assertEquals(time1, span.getBeginTime());
        Assert.assertEquals(time2, span.getEndTime());
        Assert.assertEquals(1440 / 10 , span.length());
    }

    @Test (expected=IllegalArgumentException.class)
    public void OutOfRangeTest() {
        int year = 2017;
        int month = 12;
        int day = 13;
        int hour = 2;
        int minutes = 34;

        int year2 = 2017;
        int month2 = 12;
        int day2 = 12;
        int hour2 = 2;
        int minutes2 = 34;

        ITime time1 = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year2,month2,day2,hour2,minutes2);

        TimeSpan span = new TimeSpan(time1, time2);

        Assert.assertEquals(time1, span.getBeginTime());
        Assert.assertEquals(time2, span.getEndTime());
        Assert.assertEquals(1440 / 10 , span.length());
    }

    @Test
    public void BeginEndTimeTest()
    {
        ITime begin = new Time(2016,1,1,0,0);
        ITime end = new Time(2018,6,22,0,0);

        ITime altbegin = new Time(2016,1,1,0,0);
        ITime altend = new Time(2018,6,22,3,0);

        TimeSpan span = new TimeSpan(begin, end);

        span.setBeginTime(altbegin);
        span.setEndTime(altend);

        Assert.assertEquals(altbegin.getYear(), span.getBeginTime().getYear());
        Assert.assertEquals(altend.getYear(), span.getEndTime().getYear());
    }

    @Test (expected=IllegalArgumentException.class)
    public void SetBeginOutOFRangeTest() {
        ITime begin = new Time(2016,1,1,0,0);
        ITime end = new Time(2018,6,22,0,0);

        ITime altbegin = new Time(2018,12,1,0,0);
        //ITime altend = new Time(2016,6,22,3,0);

        TimeSpan span = new TimeSpan(begin, end);

        span.setBeginTime(altbegin);
        //span.setEndTime(altend);

        Assert.assertEquals(altbegin.getYear(), span.getBeginTime().getYear());
        //Assert.assertEquals(altend.getYear(), span.getEndTime().getYear());
    }

    @Test (expected=IllegalArgumentException.class)
    public void SetEndOutOFRangeTest() {
        ITime begin = new Time(2016,1,1,0,0);
        ITime end = new Time(2018,6,22,0,0);

        //ITime altbegin = new Time(2018,12,1,0,0);
        ITime altend = new Time(2014,6,22,3,0);

        TimeSpan span = new TimeSpan(begin, end);

        //span.setBeginTime(altbegin);
        span.setEndTime(altend);

        //Assert.assertEquals(altbegin.getYear(), span.getBeginTime().getYear());
        Assert.assertEquals(altend.getYear(), span.getEndTime().getYear());
    }

    @Test
    public void changeLengthWithTest()
    {
        ITime begin = new Time(2016,6,1,3,0);
        ITime end = new Time(2018,6,22,3,0);

        TimeSpan span = new TimeSpan(begin, end);

        span.changeLengthWith(30);

        ITime altend = new Time(2018,6,22,3,30);

        Assert.assertEquals(altend.getMinutes(), span.getEndTime().getMinutes());

    }


    @Test (expected=IllegalArgumentException.class)
    public void changeLengthOutOfRangeTest()
    {
        ITime begin = new Time(2016,6,1,3,0);
        ITime end = new Time(2018,6,22,3,0);

        TimeSpan span = new TimeSpan(begin, end);

        span.changeLengthWith(-1);

        ITime altend = new Time(2018,6,22,3,30);

        Assert.assertEquals(altend.getMinutes(), span.getEndTime().getMinutes());
    }

    @Test
    public void MoveTest(){

        int year = 2015;
        int month = 12;
        int day = 12;
        int hour = 2;
        int minutes = 4;

        int year2 = 2017;
        int month2 = 11;
        int day2 = 11;
        int hour2 = 2;
        int minutes2 = 4;

        int timeToMove = 50;

        ITime time1 = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year2,month2,day2,hour2,minutes2);

        ITimeSpan span = new TimeSpan(time1, time2);
        span.move(timeToMove);

        Assert.assertNotEquals(time1, span.getBeginTime());

    }

    @Test
    public void IsPartOfTest()
    {
        int year = 2015;
        int month = 12;
        int day = 12;
        int hour = 2;
        int minutes = 4;

        int year2 = 2017;
        int month2 = 11;
        int day2 = 11;
        int hour2 = 2;
        int minutes2 = 4;

        ITime time1 = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year2,month2,day2,hour2,minutes2);

        ITimeSpan span = new TimeSpan(time1, time2);
        ITimeSpan span2 = new TimeSpan(time1, time2);
        Assert.assertTrue(span.isPartOf(span2));
    }

    @Test
    public void unionTest(){
        int year = 2015;
        int month = 10;
        int day = 10;
        int hour = 1;
        int minutes = 4;

        int year2 = 2017;
        int month2 = 11;
        int day2 = 11;
        int hour2 = 2;
        int minutes2 = 30;

        ITime time1 = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year2,month2,day2,hour2,minutes2);

        ITimeSpan span = new TimeSpan(time1, time2);
        ITimeSpan span2 = new TimeSpan(time1, time2);

        ITimeSpan unionSpan = span.unionWith(span2);

        Assert.assertEquals(unionSpan.getBeginTime(), span.getBeginTime());
        Assert.assertEquals(unionSpan.getEndTime(), span.getEndTime());


    }

    @Test
    public void unionWithOutOfRange()
    {
        int year = 2015;
        int month = 10;
        int day = 10;
        int hour = 2;
        int minutes = 30;

        int year2 = 2017;
        int month2 = 11;
        int day2 = 11;
        int hour2 = 2;
        int minutes2 = 30;

        ITime time1 = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year2,month2,day2,hour2,minutes2);

        ITimeSpan span = new TimeSpan(time1, time2);
        ITimeSpan span2 = new TimeSpan(time1, time2);

        ITimeSpan unionSpan = span.unionWith(span2);

        Assert.assertEquals(null, unionSpan);

    }

    @Test
    public void intersectionTest()
    {
        int year = 2015;
        int month = 10;
        int day = 10;
        int hour = 1;
        int minutes = 4;

        int year2 = 2017;
        int month2 = 11;
        int day2 = 11;
        int hour2 = 2;
        int minutes2 = 30;


        ITime time1 = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year2,month2,day2,hour2,minutes2);

        ITimeSpan span = new TimeSpan(time1, time2);
        ITimeSpan span2 = new TimeSpan(time1, time2);

        ITimeSpan intersectedSpan = span.intersectionWith(span2);

        Assert.assertEquals(intersectedSpan.getBeginTime(), span2.getBeginTime());
        Assert.assertEquals(intersectedSpan.getEndTime(), span2.getEndTime());
    }
}
