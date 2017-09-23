package UnitTests;

import Fontys.TimeUtil.DayInWeek;
import Fontys.TimeUtil.ITime;
import Fontys.TimeUtil.Time;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Gebruiker on 20-9-2017.
 */

public class TimeTest
{
    //Test that work
    @Test
    public void TimeConstructorTest() {
        int year = 2017;
        int month = 12;
        int day = 5;
        int hour = 12;
        int minute = 0;

        Time time = new Time(year, month, day, hour, minute);

        Assert.assertEquals(minute, time.getMinutes());
        Assert.assertEquals(year, time.getYear());
        Assert.assertEquals(month, time.getMonth());
        Assert.assertEquals(day, time.getDay());
        Assert.assertEquals(hour, time.getHours());

    }

    @Test (expected=IllegalArgumentException.class)
    public void OutOfRangeTest() {
        int year = 2017;
        int month = 12;
        int day = 5;
        int hour = 26;
        int minute = 0;

        Time time = new Time(year, month, day, hour, minute);
        Assert.assertEquals(null, time);
    }

    @Test (expected=IllegalArgumentException.class)
    public void OutOfRangeTest2() {
        int year = 2017;
        int month = 12;
        int day = 5;
        int hour = 23;
        int minute = 65;

        Time time = new Time(year, month, day, hour, minute);
        Assert.assertEquals(null, time);
    }

    @Test (expected=IllegalArgumentException.class)
    public void OutOfRangeTest3() {
        int year = 2017;
        int month = 14;
        int day = 5;
        int hour = 22;
        int minute = 0;

        Time time = new Time(year, month, day, hour, minute);
        Assert.assertEquals(null, time);
    }

    @Test (expected=IllegalArgumentException.class)
    public void OutOfRangeTest4() {
        int year = 2017;
        int month = 12;
        int day = 45;
        int hour = 23;
        int minute = 0;

        Time time = new Time(year, month, day, hour, minute);
        Assert.assertEquals(null, time);
    }


    @Test
    public void GetDayInWeekTest()
    {
        Time time2 = new Time(2017, 9, 18, 12, 0);
        DayInWeek day = time2.getDayInWeek();
        Assert.assertEquals(DayInWeek.MON, day);

        Time time3 = new Time(2017, 9, 19, 12, 0);
        DayInWeek day2 = time3.getDayInWeek();
        Assert.assertEquals(DayInWeek.TUE, day2);

        Time time = new Time(2017, 9, 20, 12, 0);
        DayInWeek day3 = time.getDayInWeek();
        Assert.assertEquals(DayInWeek.WED, day3);

        Time time4 = new Time(2017, 9, 21, 12, 0);
        DayInWeek day4 = time4.getDayInWeek();
        Assert.assertEquals(DayInWeek.THU, day4);

        Time time5 = new Time(2017, 9, 22, 12, 0);
        DayInWeek day5 = time5.getDayInWeek();
        Assert.assertEquals(DayInWeek.FRI, day5);

        Time time6 = new Time(2017, 9, 23, 12, 0);
        DayInWeek day6 = time6.getDayInWeek();
        Assert.assertEquals(DayInWeek.SAT, day6);

        Time time7 = new Time(2017, 9, 24, 12, 0);
        DayInWeek day7 = time7.getDayInWeek();
        Assert.assertEquals(DayInWeek.SUN, day7);

        //cannot be reached
//        Time time8 = new Time(2017, 1, 30, 12, 0);
//        //time8.s = 29;
//        DayInWeek day8 = time8.getDayInWeek();
//        Assert.assertEquals(null, day8);
    }

    @Test
    public void PlusTest()
    {
        int year = 2015;
        int month = 12;
        int day = 12;
        int hour = 2;
        int minutes = 34;

        int amountToAdd = 5;

        ITime time = new Time(year,month,day,hour,minutes);

        ITime times = time.plus(amountToAdd);

        Assert.assertEquals(minutes + amountToAdd, times.getMinutes());
    }

    @Test
    public void DifferenceTest()
    {
        int year = 2015;
        int month = 12;
        int day = 12;
        int hour = 2;
        int minutes = 34;
        int difference = 1;

        ITime time = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time(year + difference, month - difference , day - difference, hour - difference, minutes - difference);
        difference = time.difference(time2);

        System.out.println(difference);
        Assert.assertEquals(difference, -48233);
    }

    @Test
    public void TestCompare()
    {
        int year = 2015;
        int month = 12;
        int day = 12;
        int hour = 2;
        int minutes = 34;
        int difference = 1;

        ITime time = new Time(year,month,day,hour,minutes);
        ITime time2 = new Time
                (year + difference, month - difference , day - difference, hour - difference, minutes - difference);

        Assert.assertEquals(1 , time.compareTo(time2));
    }



}
