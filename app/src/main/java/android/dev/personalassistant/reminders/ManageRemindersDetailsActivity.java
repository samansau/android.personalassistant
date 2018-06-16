package android.dev.personalassistant.reminders;

import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.dev.personalassistant.R;
import android.dev.personalassistant.utils.Utils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;

public class ManageRemindersDetailsActivity extends AppCompatActivity{

    //Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reminders_details);

        Spinner hhSpinner=findViewById(R.id.reminderHHRepeat);
        ArrayAdapter<CharSequence> hhAdapter=ArrayAdapter.createFromResource(this,R.array.reminder_HH,android.R.layout.simple_spinner_item);
        hhSpinner.setAdapter(hhAdapter);
        hhSpinner.setSelection(0);

        Spinner mmSpinner=findViewById(R.id.reminderMMRepeat);
        ArrayAdapter<CharSequence> mmAdapter=ArrayAdapter.createFromResource(this,R.array.reminder_MM,android.R.layout.simple_spinner_item);
        mmSpinner.setAdapter(mmAdapter);
        mmSpinner.setSelection(0);

        Spinner ssSpinner=findViewById(R.id.reminderSSRepeat);
        ArrayAdapter<CharSequence> ssAdapter=ArrayAdapter.createFromResource(this,R.array.reminder_SS,android.R.layout.simple_spinner_item);
        ssSpinner.setAdapter(ssAdapter);
        ssSpinner.setSelection(0);

        Spinner ssReminderInterval=findViewById(R.id.reminderInterval);
        ArrayAdapter<CharSequence> ssReminderAdapter=ArrayAdapter.createFromResource(this,R.array.reminder_interval,android.R.layout.simple_spinner_item);
        ssReminderInterval.setAdapter(ssReminderAdapter);
        ssReminderInterval.setSelection(1);


    }

    public void toggleTextInput(View view){
        switch(view.getId()){

            case R.id.everyDayToggle:
                boolean isChecked=((Switch)findViewById(R.id.everyDayToggle)).isChecked();
                if(isChecked) {
                    ((Switch) findViewById(R.id.sundayToggle)).setChecked(!isChecked);
                    ((Switch) findViewById(R.id.mondayToggle)).setChecked(!isChecked);
                    ((Switch) findViewById(R.id.tuesdayToggle)).setChecked(!isChecked);
                    ((Switch) findViewById(R.id.wednesdayToggle)).setChecked(!isChecked);
                    ((Switch) findViewById(R.id.thursdayToggle)).setChecked(!isChecked);
                    ((Switch) findViewById(R.id.fridayToggle)).setChecked(!isChecked);
                    ((Switch) findViewById(R.id.saturdayToggle)).setChecked(!isChecked);
                }

                EditText editFromReminder=(EditText)findViewById(R.id.reminderFromTime);
                editFromReminder.setEnabled(isChecked);
                editFromReminder.setText("");


                EditText editToReminder=(EditText)findViewById(R.id.reminderToTime);
                editToReminder.setEnabled(isChecked);
                editToReminder.setText("");
                break;

            case R.id.sundayToggle:
                boolean isSundayChecked=((Switch)findViewById(R.id.sundayToggle)).isChecked();
                if(isSundayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isSundayChecked);
                EditText editFromReminderSunday=(EditText)findViewById(R.id.reminderFromTimeSunday);
                editFromReminderSunday.setEnabled(isSundayChecked);
                editFromReminderSunday.setText("");


                EditText editToReminderSunday=(EditText)findViewById(R.id.reminderToTimeSunday);
                editToReminderSunday.setEnabled(isSundayChecked);
                editToReminderSunday.setText("");
                break;

            case R.id.mondayToggle:
                boolean isMondayChecked=((Switch)findViewById(R.id.mondayToggle)).isChecked();
                if(isMondayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isMondayChecked);
                EditText editFromReminderMonday=(EditText)findViewById(R.id.reminderFromTimeMonday);
                editFromReminderMonday.setEnabled(isMondayChecked);
                editFromReminderMonday.setText("");


                EditText editToReminderMonday=(EditText)findViewById(R.id.reminderToTimeMonday);
                editToReminderMonday.setEnabled(isMondayChecked);
                editToReminderMonday.setText("");
                break;

            case R.id.tuesdayToggle:
                boolean isTuesdayChecked=((Switch)findViewById(R.id.tuesdayToggle)).isChecked();
                if(isTuesdayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isTuesdayChecked);
                EditText editFromReminderTuesday=(EditText)findViewById(R.id.reminderFromTimeTuesday);
                editFromReminderTuesday.setEnabled(isTuesdayChecked);
                editFromReminderTuesday.setText("");


                EditText editToReminderTuesday=(EditText)findViewById(R.id.reminderToTimeTuesday);
                editToReminderTuesday.setEnabled(isTuesdayChecked);
                editToReminderTuesday.setText("");
                break;

            case R.id.wednesdayToggle:
                boolean isWednesdayChecked=((Switch)findViewById(R.id.wednesdayToggle)).isChecked();
                if(isWednesdayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isWednesdayChecked);
                EditText editFromReminderWednesday=(EditText)findViewById(R.id.reminderFromTimeWednesday);
                editFromReminderWednesday.setEnabled(isWednesdayChecked);
                editFromReminderWednesday.setText("");


                EditText editToReminderWednesday=(EditText)findViewById(R.id.reminderToTimeWednesday);
                editToReminderWednesday.setEnabled(isWednesdayChecked);
                editToReminderWednesday.setText("");
                break;

            case R.id.thursdayToggle:
                boolean isThursdayChecked=((Switch)findViewById(R.id.thursdayToggle)).isChecked();
                if(isThursdayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isThursdayChecked);
                EditText editFromReminderThursday=(EditText)findViewById(R.id.reminderFromTimeThursday);
                editFromReminderThursday.setEnabled(isThursdayChecked);
                editFromReminderThursday.setText("");


                EditText editToReminderThursday=(EditText)findViewById(R.id.reminderToTimeThursday);
                editToReminderThursday.setEnabled(isThursdayChecked);
                editToReminderThursday.setText("");
                break;

            case R.id.fridayToggle:
                boolean isFridayChecked=((Switch)findViewById(R.id.fridayToggle)).isChecked();
                if(isFridayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isFridayChecked);
                EditText editFromReminderFriday=(EditText)findViewById(R.id.reminderFromTimeFriday);
                editFromReminderFriday.setEnabled(isFridayChecked);
                editFromReminderFriday.setText("");


                EditText editToReminderFriday=(EditText)findViewById(R.id.reminderToTimeFriday);
                editToReminderFriday.setEnabled(isFridayChecked);
                editToReminderFriday.setText("");
                break;

            case R.id.saturdayToggle:
                boolean isSaturdayChecked=((Switch)findViewById(R.id.saturdayToggle)).isChecked();
                if(isSaturdayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isSaturdayChecked);
                EditText editFromReminderSaturday=(EditText)findViewById(R.id.reminderFromTimeSaturday);
                editFromReminderSaturday.setEnabled(isSaturdayChecked);
                editFromReminderSaturday.setText("");


                EditText editToReminderSaturday=(EditText)findViewById(R.id.reminderToTimeSaturday);
                editToReminderSaturday.setEnabled(isSaturdayChecked);
                editToReminderSaturday.setText("");
                break;


        }


    }

    public void populateFromDate(View view){
        EditText fromDate=(EditText)findViewById(R.id.reminderFromDate);
        Utils.populateDateField(fromDate,view.getContext());
    }

    public void populateToDate(View view){
        EditText toDate=(EditText)findViewById(R.id.reminderToDate);
        Utils.populateDateField(toDate,view.getContext());
    }


    public void populateTime(View view){
        switch(view.getId()){
            case R.id.reminderFromTime:
                EditText fromDate=(EditText)findViewById(R.id.reminderFromTime);
                Utils.populateTimeField(fromDate,view.getContext());
                break;

            case R.id.reminderToTime:
                EditText toDate=(EditText)findViewById(R.id.reminderToTime);
                Utils.populateTimeField(toDate,view.getContext());
                break;

            case R.id.reminderFromTimeSunday:
                EditText fromDateSunday=(EditText)findViewById(R.id.reminderFromTimeSunday);
                Utils.populateTimeField(fromDateSunday,view.getContext());
                break;

            case R.id.reminderToTimeSunday:
                EditText toDateSunday=(EditText)findViewById(R.id.reminderToTimeSunday);
                Utils.populateTimeField(toDateSunday,view.getContext());
                break;

            case R.id.reminderFromTimeMonday:
                EditText fromDateMonday=(EditText)findViewById(R.id.reminderFromTimeMonday);
                Utils.populateTimeField(fromDateMonday,view.getContext());
                break;

            case R.id.reminderToTimeMonday:
                EditText toDateMonday=(EditText)findViewById(R.id.reminderToTimeMonday);
                Utils.populateTimeField(toDateMonday,view.getContext());
                break;

            case R.id.reminderFromTimeTuesday:
                EditText fromDateTuesday=(EditText)findViewById(R.id.reminderFromTimeTuesday);
                Utils.populateTimeField(fromDateTuesday,view.getContext());
                break;

            case R.id.reminderToTimeTuesday:
                EditText toDateTuesday=(EditText)findViewById(R.id.reminderToTimeTuesday);
                Utils.populateTimeField(toDateTuesday,view.getContext());
                break;

            case R.id.reminderFromTimeWednesday:
                EditText fromDateWednesday=(EditText)findViewById(R.id.reminderFromTimeWednesday);
                Utils.populateTimeField(fromDateWednesday,view.getContext());
                break;

            case R.id.reminderToTimeWednesday:
                EditText toDateWednesday=(EditText)findViewById(R.id.reminderToTimeWednesday);
                Utils.populateTimeField(toDateWednesday,view.getContext());
                break;

            case R.id.reminderFromTimeThursday:
                EditText fromDateThursday=(EditText)findViewById(R.id.reminderFromTimeThursday);
                Utils.populateTimeField(fromDateThursday,view.getContext());
                break;

            case R.id.reminderToTimeThursday:
                EditText toDateThursday=(EditText)findViewById(R.id.reminderToTimeThursday);
                Utils.populateTimeField(toDateThursday,view.getContext());
                break;

            case R.id.reminderFromTimeFriday:
                EditText fromDateFriday=(EditText)findViewById(R.id.reminderFromTimeFriday);
                Utils.populateTimeField(fromDateFriday,view.getContext());
                break;

            case R.id.reminderToTimeFriday:
                EditText toDateFriday=(EditText)findViewById(R.id.reminderToTimeFriday);
                Utils.populateTimeField(toDateFriday,view.getContext());
                break;

            case R.id.reminderFromTimeSaturday:
                EditText fromDateSaturday=(EditText)findViewById(R.id.reminderFromTimeSaturday);
                Utils.populateTimeField(fromDateSaturday,view.getContext());
                break;

            case R.id.reminderToTimeSaturday:
                EditText toDateSaturday=(EditText)findViewById(R.id.reminderToTimeSaturday);
                Utils.populateTimeField(toDateSaturday,view.getContext());
                break;
        }

    }






}
