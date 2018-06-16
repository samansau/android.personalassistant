package android.dev.personalassistant.dao.reminder;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.dev.personalassistant.entities.reminder.Reminder;

import java.util.List;

/**
 * Created by saurabh on 5/29/18.
 */

@Dao
public interface ReminderDAO {
    @Insert
    public void insertReminder(List<Reminder> reminders);


    @Insert
    public void insertReminder(Reminder reminder);

    @Update
    public void updateReminder(Reminder reminder);


    @Query("select * from Reminder")
    public List<Reminder> fetchAllReminders();

    @Query("select * from Reminder where reminderName =:reminderName")
    public Reminder fetchReminderByReminderName(String reminderName);


}
