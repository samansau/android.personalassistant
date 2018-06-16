package android.dev.personalassistant.entities.reminder;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Reminder {
    @PrimaryKey(autoGenerate = true)
    private int reminderId;

    @NonNull
    private String reminderName;
    private String fromDate;
    private String toDate;

    private String fromTime;
    private String toTime;

    private String fromTimeSunday;
    private String toTimeSunday;

    private String fromTimeMonday;
    private String toTimeMonday;

    private String fromTimeTuesday;
    private String toTimeTuesday;

    private String fromTimeWednesday;
    private String toTimeWednesday;


    private String fromTimeThursday;
    private String toTimeThursday;

    private String fromTimeFriday;
    private String toTimeFriday;


    private String fromTimeSaturday;
    private String toTimeSaturday;

    private int repeatEveryHH;
    private int repeatEveryMM;
    private int repeatEverySS;
    private int interval;


    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public String getReminderName() {
        return reminderName;
    }

    public void setReminderName(String reminderName) {
        this.reminderName = reminderName;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public String getFromTimeSunday() {
        return fromTimeSunday;
    }

    public void setFromTimeSunday(String fromTimeSunday) {
        this.fromTimeSunday = fromTimeSunday;
    }

    public String getToTimeSunday() {
        return toTimeSunday;
    }

    public void setToTimeSunday(String toTimeSunday) {
        this.toTimeSunday = toTimeSunday;
    }

    public String getFromTimeMonday() {
        return fromTimeMonday;
    }

    public void setFromTimeMonday(String fromTimeMonday) {
        this.fromTimeMonday = fromTimeMonday;
    }

    public String getToTimeMonday() {
        return toTimeMonday;
    }

    public void setToTimeMonday(String toTimeMonday) {
        this.toTimeMonday = toTimeMonday;
    }

    public String getFromTimeTuesday() {
        return fromTimeTuesday;
    }

    public void setFromTimeTuesday(String fromTimeTuesday) {
        this.fromTimeTuesday = fromTimeTuesday;
    }

    public String getToTimeTuesday() {
        return toTimeTuesday;
    }

    public void setToTimeTuesday(String toTimeTuesday) {
        this.toTimeTuesday = toTimeTuesday;
    }

    public String getFromTimeWednesday() {
        return fromTimeWednesday;
    }

    public void setFromTimeWednesday(String fromTimeWednesday) {
        this.fromTimeWednesday = fromTimeWednesday;
    }

    public String getToTimeWednesday() {
        return toTimeWednesday;
    }

    public void setToTimeWednesday(String toTimeWednesday) {
        this.toTimeWednesday = toTimeWednesday;
    }

    public String getFromTimeThursday() {
        return fromTimeThursday;
    }

    public void setFromTimeThursday(String fromTimeThursday) {
        this.fromTimeThursday = fromTimeThursday;
    }

    public String getToTimeThursday() {
        return toTimeThursday;
    }

    public void setToTimeThursday(String toTimeThursday) {
        this.toTimeThursday = toTimeThursday;
    }

    public String getFromTimeFriday() {
        return fromTimeFriday;
    }

    public void setFromTimeFriday(String fromTimeFriday) {
        this.fromTimeFriday = fromTimeFriday;
    }

    public String getToTimeFriday() {
        return toTimeFriday;
    }

    public void setToTimeFriday(String toTimeFriday) {
        this.toTimeFriday = toTimeFriday;
    }

    public String getFromTimeSaturday() {
        return fromTimeSaturday;
    }

    public void setFromTimeSaturday(String fromTimeSaturday) {
        this.fromTimeSaturday = fromTimeSaturday;
    }

    public String getToTimeSaturday() {
        return toTimeSaturday;
    }

    public void setToTimeSaturday(String toTimeSaturday) {
        this.toTimeSaturday = toTimeSaturday;
    }

    public int getRepeatEveryHH() {
        return repeatEveryHH;
    }

    public void setRepeatEveryHH(int repeatEveryHH) {
        this.repeatEveryHH = repeatEveryHH;
    }

    public int getRepeatEveryMM() {
        return repeatEveryMM;
    }

    public void setRepeatEveryMM(int repeatEveryMM) {
        this.repeatEveryMM = repeatEveryMM;
    }

    public int getRepeatEverySS() {
        return repeatEverySS;
    }

    public void setRepeatEverySS(int repeatEverySS) {
        this.repeatEverySS = repeatEverySS;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }
}
