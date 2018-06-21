package android.dev.personalassistant.dao;

import android.arch.persistence.room.RoomDatabase;
import android.dev.personalassistant.dao.expense.ExpenseTagDAO;
import android.dev.personalassistant.dao.investment.FixedDepositDAO;
import android.dev.personalassistant.dao.kym.BankAccountDAO;
import android.dev.personalassistant.dao.kym.CarDAO;
import android.dev.personalassistant.dao.kym.CardDAO;
import android.dev.personalassistant.dao.kym.PersonDAO;
import android.dev.personalassistant.dao.reminder.ReminderDAO;
import android.dev.personalassistant.entities.expense.ExpenseTag;
import android.dev.personalassistant.entities.kym.BankAccount;
import android.dev.personalassistant.entities.kym.Car;
import android.dev.personalassistant.entities.kym.Card;
import android.dev.personalassistant.entities.kym.Person;
import android.dev.personalassistant.entities.reminder.Reminder;

/**
 * Created by saurabh on 5/29/18.
 */

@android.arch.persistence.room.Database(entities =
        {BankAccount.class, Card.class,Person.class, Car.class, Reminder.class, ExpenseTag.class},version = 2,exportSchema = false)
public abstract class PersonalAssistantDatabase extends RoomDatabase{
    public abstract BankAccountDAO getBankAccountDAO();
    public abstract CardDAO getCardDAO();
    public abstract PersonDAO getPersonDAO();
    public abstract CarDAO getCarDAO();
    public abstract ReminderDAO getReminderDAO();
    public abstract ExpenseTagDAO getExpenseTagDAO();



}
