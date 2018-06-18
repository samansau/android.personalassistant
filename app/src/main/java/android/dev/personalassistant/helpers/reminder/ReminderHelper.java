package android.dev.personalassistant.helpers.reminder;

import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.kym.BankAccount;
import android.dev.personalassistant.entities.kym.Card;
import android.dev.personalassistant.entities.reminder.Reminder;
import android.dev.personalassistant.vo.kym.CardVO;
import android.dev.personalassistant.vo.reminder.ReminderVO;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saurabh on 6/2/18.
 */

public class ReminderHelper {

    public void deleteReminder(final PersonalAssistantDatabase personalAssistantDatabase, final String reminderName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Reminder reminder = new Reminder();
                try {
                    ReminderVO reminderVOOOld=fetchReminderVOByName(personalAssistantDatabase, reminderName);
                    reminder.setReminderId(reminderVOOOld.getReminderId());
                    personalAssistantDatabase.getReminderDAO().deleteReminder(reminder);
                }catch (InterruptedException ie){
                    Log.e("deleteCard",ie.getStackTrace().toString());
                }

            }
        }).start();
    }

    public void persistReminder(final PersonalAssistantDatabase personalAssistantDatabase, final ReminderVO reminderVO){
        new Thread(new Runnable() {
            @Override
            public void run() {
                int reminderIdValue=reminderVO.getReminderId();
                Reminder reminder = new Reminder();
                reminder.setReminderName(reminderVO.getReminderName());

                reminder.setFromDate(reminderVO.getFromDate());
                reminder.setToDate(reminderVO.getToDate());

                reminder.setFromTime(reminderVO.getFromTime());
                reminder.setToTime(reminderVO.getToTime());

                reminder.setFromTimeSunday(reminderVO.getFromTimeSunday());
                reminder.setFromTimeMonday(reminderVO.getFromTimeMonday());
                reminder.setFromTimeTuesday(reminderVO.getFromTimeTuesday());
                reminder.setFromTimeWednesday(reminderVO.getFromTimeWednesday());
                reminder.setFromTimeThursday(reminderVO.getFromTimeThursday());
                reminder.setFromTimeFriday(reminderVO.getFromTimeFriday());
                reminder.setFromTimeSaturday(reminderVO.getFromTimeSaturday());

                reminder.setToTimeSunday(reminderVO.getToTimeSunday());
                reminder.setToTimeMonday(reminderVO.getToTimeMonday());
                reminder.setToTimeTuesday(reminderVO.getToTimeTuesday());
                reminder.setToTimeWednesday(reminderVO.getToTimeWednesday());
                reminder.setToTimeThursday(reminderVO.getToTimeThursday());
                reminder.setToTimeFriday(reminderVO.getToTimeFriday());
                reminder.setToTimeSaturday(reminderVO.getToTimeSaturday());

                reminder.setRepeatEveryHH(reminderVO.getRepeatEveryHH());
                reminder.setRepeatEveryMM(reminderVO.getRepeatEveryMM());
                reminder.setRepeatEverySS(reminderVO.getRepeatEverySS());
                reminder.setInterval(reminderVO.getInterval());

                reminder.setReminderOn(reminderVO.isReminderOn());
                reminder.setEveryDayOn(reminderVO.isEveryDayOn());
                reminder.setSundayOn(reminderVO.isSundayOn());
                reminder.setMondayOn(reminderVO.isMondayOn());
                reminder.setTuesdayOn(reminderVO.isTuesdayOn());
                reminder.setWednesdayOn(reminderVO.isWednesdayOn());
                reminder.setThursdayOn(reminderVO.isThursdayOn());
                reminder.setFridayOn(reminderVO.isFridayOn());
                reminder.setSaturdayOn(reminderVO.isSaturdayOn());




                if (reminderIdValue >= 0) {
                    reminder.setReminderId(reminderVO.getReminderId());
                    personalAssistantDatabase.getReminderDAO().updateReminder(reminder);
                }else{
                    personalAssistantDatabase.getReminderDAO().insertReminder(reminder);
                }

            }
        }).start();
    }

    public ReminderVO fetchReminderVOByName(final PersonalAssistantDatabase personalAssistantDatabase,String reminderName) throws InterruptedException{
        final ReminderVO reminderVO=new ReminderVO();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                Reminder reminder = personalAssistantDatabase.getReminderDAO().fetchReminderByReminderName(reminderName);
                if(reminder!=null){


                        reminderVO.setReminderName(reminder.getReminderName());
                        reminderVO.setReminderId(reminder.getReminderId());

                        reminderVO.setFromDate(reminder.getFromDate());
                        reminderVO.setToDate(reminder.getToDate());

                        reminderVO.setFromTime(reminder.getFromTime());
                        reminderVO.setToTime(reminder.getToTime());

                        reminderVO.setFromTimeSunday(reminder.getFromTimeSunday());
                        reminderVO.setFromTimeMonday(reminder.getFromTimeMonday());
                        reminderVO.setFromTimeTuesday(reminder.getFromTimeTuesday());
                        reminderVO.setFromTimeWednesday(reminder.getFromTimeWednesday());
                        reminderVO.setFromTimeThursday(reminder.getFromTimeThursday());
                        reminderVO.setFromTimeFriday(reminder.getFromTimeFriday());
                        reminderVO.setFromTimeSaturday(reminder.getFromTimeSaturday());

                        reminderVO.setToTimeSunday(reminder.getToTimeSunday());
                        reminderVO.setToTimeMonday(reminder.getToTimeMonday());
                        reminderVO.setToTimeTuesday(reminder.getToTimeTuesday());
                        reminderVO.setToTimeWednesday(reminder.getToTimeWednesday());
                        reminderVO.setToTimeThursday(reminder.getToTimeThursday());
                        reminderVO.setToTimeFriday(reminder.getToTimeFriday());
                        reminderVO.setToTimeSaturday(reminder.getToTimeSaturday());

                        reminderVO.setRepeatEveryHH(reminder.getRepeatEveryHH());
                        reminderVO.setRepeatEveryMM(reminder.getRepeatEveryMM());
                        reminderVO.setRepeatEverySS(reminder.getRepeatEverySS());
                        reminderVO.setInterval(reminder.getInterval());

                        reminderVO.setReminderOn(reminder.isReminderOn());
                        reminderVO.setEveryDayOn(reminder.isEveryDayOn());
                        reminderVO.setSundayOn(reminder.isSundayOn());
                        reminderVO.setMondayOn(reminder.isMondayOn());
                        reminderVO.setTuesdayOn(reminder.isTuesdayOn());
                        reminderVO.setWednesdayOn(reminder.isWednesdayOn());
                        reminderVO.setThursdayOn(reminder.isThursdayOn());
                        reminderVO.setFridayOn(reminder.isFridayOn());
                        reminderVO.setSaturdayOn(reminder.isSaturdayOn());



                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return reminderVO;
    }


    public List<ReminderVO> fetchAllReminderVOs(final PersonalAssistantDatabase personalAssistantDatabase) throws InterruptedException{
        final List<ReminderVO> reminderVOs=new ArrayList<>();
        Thread fetchThread=new Thread(new Runnable() {
            @Override
            public void run() {
                List<Reminder> reminders = personalAssistantDatabase.getReminderDAO().fetchAllReminders();
                if(reminders!=null){
                    for(Reminder reminder:reminders){
                        ReminderVO reminderVO=new ReminderVO();

                        reminderVO.setReminderId(reminder.getReminderId());
                        reminderVO.setReminderName(reminder.getReminderName());

                        reminderVO.setFromDate(reminder.getFromDate());
                        reminderVO.setToDate(reminder.getToDate());

                        reminderVO.setFromTime(reminder.getFromTime());
                        reminderVO.setToTime(reminder.getToTime());

                        reminderVO.setFromTimeSunday(reminder.getFromTimeSunday());
                        reminderVO.setFromTimeMonday(reminder.getFromTimeMonday());
                        reminderVO.setFromTimeTuesday(reminder.getFromTimeTuesday());
                        reminderVO.setFromTimeWednesday(reminder.getFromTimeWednesday());
                        reminderVO.setFromTimeThursday(reminder.getFromTimeThursday());
                        reminderVO.setFromTimeFriday(reminder.getFromTimeFriday());
                        reminderVO.setFromTimeSaturday(reminder.getFromTimeSaturday());

                        reminderVO.setToTimeSunday(reminder.getToTimeSunday());
                        reminderVO.setToTimeMonday(reminder.getToTimeMonday());
                        reminderVO.setToTimeTuesday(reminder.getToTimeTuesday());
                        reminderVO.setToTimeWednesday(reminder.getToTimeWednesday());
                        reminderVO.setToTimeThursday(reminder.getToTimeThursday());
                        reminderVO.setToTimeFriday(reminder.getToTimeFriday());
                        reminderVO.setToTimeSaturday(reminder.getToTimeSaturday());

                        reminderVO.setRepeatEveryHH(reminder.getRepeatEveryHH());
                        reminderVO.setRepeatEveryMM(reminder.getRepeatEveryMM());
                        reminderVO.setRepeatEverySS(reminder.getRepeatEverySS());
                        reminderVO.setInterval(reminder.getInterval());

                        reminderVO.setReminderOn(reminder.isReminderOn());
                        reminderVO.setEveryDayOn(reminder.isEveryDayOn());
                        reminderVO.setSundayOn(reminder.isSundayOn());
                        reminderVO.setMondayOn(reminder.isMondayOn());
                        reminderVO.setTuesdayOn(reminder.isTuesdayOn());
                        reminderVO.setWednesdayOn(reminder.isWednesdayOn());
                        reminderVO.setThursdayOn(reminder.isThursdayOn());
                        reminderVO.setFridayOn(reminder.isFridayOn());
                        reminderVO.setSaturdayOn(reminder.isSaturdayOn());


                        reminderVOs.add(reminderVO);
                    }
                }
            }
        });
        fetchThread.start();
        fetchThread.join();
        return reminderVOs;
    }
}
