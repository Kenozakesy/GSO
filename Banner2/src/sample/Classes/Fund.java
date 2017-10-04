package sample.Classes;

import sample.Interfaces.IFunds;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public class Fund implements IFunds {

    private String name;
    private double rate;

    public Fund(String name, double rate)
    {
        this.name = name;
        this.rate = rate;
    }

    @Override
    public String GetName() {
        return null;
    }

    @Override
    public double GetRate() {
        return 0;
    }

    public void setRate(double newKoers)
    {
        this.rate = newKoers;
    }
}
