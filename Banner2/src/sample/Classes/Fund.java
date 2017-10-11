package sample.Classes;

import sample.Interfaces.IFunds;

import java.io.Serializable;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public class Fund implements IFunds, Serializable {

    private String name;
    private double rate;

    public Fund(String name, double rate)
    {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public double GetRate() {
        return rate;
    }

    public void setRate(double newKoers)
    {
        this.rate = newKoers;
    }
}
