package sample.Classes;

import sample.Interfaces.IEffectsExchange;
import sample.Interfaces.IFunds;

import java.util.List;

/**
 * Created by Gebruiker on 4-10-2017.
 */
public class MockEffectChange implements IEffectsExchange {


    @Override
    public List<IFunds> GetRates() {
        return null;
    }
}
