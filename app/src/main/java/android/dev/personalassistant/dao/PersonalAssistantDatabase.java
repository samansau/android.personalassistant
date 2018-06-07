package android.dev.personalassistant.dao;

import android.arch.persistence.room.RoomDatabase;
import android.dev.personalassistant.entities.BankAccount;
import android.dev.personalassistant.entities.Car;
import android.dev.personalassistant.entities.Card;
import android.dev.personalassistant.entities.Person;

/**
 * Created by saurabh on 5/29/18.
 */

@android.arch.persistence.room.Database(entities =
        {BankAccount.class, Card.class,Person.class, Car.class},version = 1,exportSchema = false)
public abstract class PersonalAssistantDatabase extends RoomDatabase{
    public abstract BankAccountDAO getBankAccountDAO();
    public abstract CardDAO getCardDAO();
    public abstract PersonDAO getPersonDAO();
    public abstract CarDAO getCarDAO();







}
