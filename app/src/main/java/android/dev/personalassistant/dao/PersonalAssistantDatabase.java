package android.dev.personalassistant.dao;

import android.arch.persistence.room.RoomDatabase;
import android.dev.personalassistant.entities.BankAccount;

/**
 * Created by saurabh on 5/29/18.
 */

@android.arch.persistence.room.Database(entities =
        {BankAccount.class},version = 1,exportSchema = false)
public abstract class PersonalAssistantDatabase extends RoomDatabase{
    public abstract BankAccountDAO getBankAccountDAO();


}
