package android.dev.personalassistant.reminders;

import android.app.DatePickerDialog;
import android.app.ListActivity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.entities.reminder.Reminder;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.helpers.reminder.ReminderHelper;
import android.dev.personalassistant.services.reminder.ReminderService;
import android.dev.personalassistant.utils.Utils;
import android.dev.personalassistant.vo.reminder.ReminderVO;
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

import static android.dev.personalassistant.utils.Keys.reminderFromDate;
import static android.dev.personalassistant.utils.Keys.reminderFromTime;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeFriday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeMonday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeSaturday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeSunday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeThursday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeTuesday;
import static android.dev.personalassistant.utils.Keys.reminderFromTimeWednesday;
import static android.dev.personalassistant.utils.Keys.reminderHHKey;
import static android.dev.personalassistant.utils.Keys.reminderIdKey;
import static android.dev.personalassistant.utils.Keys.reminderInterval;
import static android.dev.personalassistant.utils.Keys.reminderMinKey;
import static android.dev.personalassistant.utils.Keys.reminderName;
import static android.dev.personalassistant.utils.Keys.reminderRepeatHH;
import static android.dev.personalassistant.utils.Keys.reminderRepeatMM;
import static android.dev.personalassistant.utils.Keys.reminderRepeatSS;
import static android.dev.personalassistant.utils.Keys.reminderSecKey;
import static android.dev.personalassistant.utils.Keys.reminderToDate;
import static android.dev.personalassistant.utils.Keys.reminderToTime;
import static android.dev.personalassistant.utils.Keys.reminderToTimeFriday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeMonday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeSaturday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeSunday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeThursday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeTuesday;
import static android.dev.personalassistant.utils.Keys.reminderToTimeWednesday;

public class ManageRemindersDetailsActivity extends AppCompatActivity{

    int reminderId=-1;


    EditText fromDate,toDate,editReminderName,editFromReminder,editToReminder,editFromReminderSunday,editToReminderSunday,editFromReminderMonday,editToReminderMonday,
            editFromReminderTuesday,editToReminderTuesday,editToReminderWednesday,editFromReminderWednesday,
            editToReminderThursday,editFromReminderThursday,editToReminderFriday,editFromReminderFriday,
            editToReminderSaturday,editFromReminderSaturday;

    Spinner hhSpinner,mmSpinner,ssSpinner,ssReminderInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reminders_details);

        editReminderName=findViewById(R.id.reminderName);

        hhSpinner=findViewById(R.id.reminderHHRepeat);
        ArrayAdapter<CharSequence> hhAdapter=ArrayAdapter.createFromResource(this,R.array.reminder_HH,android.R.layout.simple_spinner_item);
        hhSpinner.setAdapter(hhAdapter);
        hhSpinner.setSelection(0);

        mmSpinner=findViewById(R.id.reminderMMRepeat);
        ArrayAdapter<CharSequence> mmAdapter=ArrayAdapter.createFromResource(this,R.array.reminder_MM,android.R.layout.simple_spinner_item);
        mmSpinner.setAdapter(mmAdapter);
        mmSpinner.setSelection(0);

        ssSpinner=findViewById(R.id.reminderSSRepeat);
        ArrayAdapter<CharSequence> ssAdapter=ArrayAdapter.createFromResource(this,R.array.reminder_SS,android.R.layout.simple_spinner_item);
        ssSpinner.setAdapter(ssAdapter);
        ssSpinner.setSelection(0);

        ssReminderInterval=findViewById(R.id.reminderInterval);
        ArrayAdapter<CharSequence> ssReminderAdapter=ArrayAdapter.createFromResource(this,R.array.reminder_interval,android.R.layout.simple_spinner_item);
        ssReminderInterval.setAdapter(ssReminderAdapter);
        ssReminderInterval.setSelection(1);


        fromDate=(EditText)findViewById(R.id.reminderFromTime);
        toDate=(EditText)findViewById(R.id.reminderToTime);
        editFromReminder=(EditText)findViewById(R.id.reminderFromTime);
        editToReminder=(EditText)findViewById(R.id.reminderToTime);
        editFromReminderSunday=(EditText)findViewById(R.id.reminderFromTimeSunday);
        editToReminderSunday=(EditText)findViewById(R.id.reminderToTimeSunday);
        editFromReminderMonday=(EditText)findViewById(R.id.reminderFromTimeMonday);
        editToReminderMonday=(EditText)findViewById(R.id.reminderToTimeMonday);
        editFromReminderTuesday=(EditText)findViewById(R.id.reminderFromTimeTuesday);
        editToReminderTuesday=(EditText)findViewById(R.id.reminderToTimeTuesday);
        editFromReminderWednesday=(EditText)findViewById(R.id.reminderFromTimeWednesday);
        editToReminderWednesday=(EditText)findViewById(R.id.reminderToTimeWednesday);
        editFromReminderThursday=(EditText)findViewById(R.id.reminderFromTimeThursday);
        editToReminderThursday=(EditText)findViewById(R.id.reminderToTimeThursday);
        editFromReminderFriday=(EditText)findViewById(R.id.reminderFromTimeFriday);
        editToReminderFriday=(EditText)findViewById(R.id.reminderToTimeFriday);
        editFromReminderSaturday=(EditText)findViewById(R.id.reminderFromTimeSaturday);
        editToReminderSaturday=(EditText)findViewById(R.id.reminderToTimeSaturday);


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            reminderId=bundle.getInt(reminderIdKey);
            editReminderName.setText(bundle.getString(reminderName));
            fromDate.setText(bundle.getString(reminderFromDate));
            toDate.setText(bundle.getString(reminderToDate ));
            editFromReminder.setText(bundle.getString(reminderFromTime));
            editToReminder.setText(bundle.getString(reminderToTime));
            editFromReminderSunday.setText(bundle.getString(reminderFromTimeSunday));
            editToReminderSunday.setText(bundle.getString(reminderToTimeSunday));
            editFromReminderMonday.setText(bundle.getString(reminderFromTimeMonday));
            editToReminderMonday.setText(bundle.getString(reminderToTimeMonday));
            editFromReminderTuesday.setText(bundle.getString(reminderFromTimeTuesday));
            editToReminderTuesday.setText(bundle.getString(reminderToTimeTuesday));
            editFromReminderWednesday.setText(bundle.getString(reminderFromTimeWednesday));
            editToReminderWednesday.setText(bundle.getString(reminderToTimeWednesday));
            editFromReminderThursday.setText(bundle.getString(reminderFromTimeThursday));
            editToReminderThursday.setText(bundle.getString(reminderToTimeThursday));
            editFromReminderFriday.setText(bundle.getString(reminderFromTimeFriday));
            editToReminderFriday.setText(bundle.getString(reminderToTimeFriday));
            editFromReminderSaturday.setText(bundle.getString(reminderFromTimeSaturday));
            editToReminderSaturday.setText(bundle.getString(reminderToTimeSaturday));

            int hhPosition =hhAdapter.getPosition(Utils.padZeroes(bundle.getInt(reminderRepeatHH),2)+" "+reminderHHKey);
            hhSpinner.setSelection(hhPosition);

            int mmPosition =mmAdapter.getPosition(Utils.padZeroes(bundle.getInt(reminderRepeatMM),2)+" "+reminderMinKey);
            mmSpinner.setSelection(mmPosition);

            int ssPosition =ssAdapter.getPosition(Utils.padZeroes(bundle.getInt(reminderRepeatSS),2)+" "+reminderSecKey);
            ssSpinner.setSelection(ssPosition);

            int ssIntervalPosition =ssReminderAdapter.getPosition(Utils.padZeroes(bundle.getInt(reminderInterval),2)+" "+reminderSecKey);
            ssReminderInterval.setSelection(ssIntervalPosition);

        }


    }

    public void toggleReminders(View view){
        Switch reminderSwitch=view.findViewById(R.id.remindersToggle);
        Intent serviceIntent=new Intent(getBaseContext(), ReminderService.class);
        if(reminderSwitch.isChecked()){
            startService(serviceIntent);
        }else{

            stopService(serviceIntent);
        }
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


                editFromReminder.setEnabled(isChecked);
                editFromReminder.setText("");



                editToReminder.setEnabled(isChecked);
                editToReminder.setText("");
                break;

            case R.id.sundayToggle:
                boolean isSundayChecked=((Switch)findViewById(R.id.sundayToggle)).isChecked();
                if(isSundayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isSundayChecked);

                editFromReminderSunday.setEnabled(isSundayChecked);
                editFromReminderSunday.setText("");



                editToReminderSunday.setEnabled(isSundayChecked);
                editToReminderSunday.setText("");
                break;

            case R.id.mondayToggle:
                boolean isMondayChecked=((Switch)findViewById(R.id.mondayToggle)).isChecked();
                if(isMondayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isMondayChecked);

                editFromReminderMonday.setEnabled(isMondayChecked);
                editFromReminderMonday.setText("");



                editToReminderMonday.setEnabled(isMondayChecked);
                editToReminderMonday.setText("");
                break;

            case R.id.tuesdayToggle:
                boolean isTuesdayChecked=((Switch)findViewById(R.id.tuesdayToggle)).isChecked();
                if(isTuesdayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isTuesdayChecked);

                editFromReminderTuesday.setEnabled(isTuesdayChecked);
                editFromReminderTuesday.setText("");



                editToReminderTuesday.setEnabled(isTuesdayChecked);
                editToReminderTuesday.setText("");
                break;

            case R.id.wednesdayToggle:
                boolean isWednesdayChecked=((Switch)findViewById(R.id.wednesdayToggle)).isChecked();
                if(isWednesdayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isWednesdayChecked);

                editFromReminderWednesday.setEnabled(isWednesdayChecked);
                editFromReminderWednesday.setText("");



                editToReminderWednesday.setEnabled(isWednesdayChecked);
                editToReminderWednesday.setText("");
                break;

            case R.id.thursdayToggle:
                boolean isThursdayChecked=((Switch)findViewById(R.id.thursdayToggle)).isChecked();
                if(isThursdayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isThursdayChecked);

                editFromReminderThursday.setEnabled(isThursdayChecked);
                editFromReminderThursday.setText("");



                editToReminderThursday.setEnabled(isThursdayChecked);
                editToReminderThursday.setText("");
                break;

            case R.id.fridayToggle:
                boolean isFridayChecked=((Switch)findViewById(R.id.fridayToggle)).isChecked();
                if(isFridayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isFridayChecked);

                editFromReminderFriday.setEnabled(isFridayChecked);
                editFromReminderFriday.setText("");



                editToReminderFriday.setEnabled(isFridayChecked);
                editToReminderFriday.setText("");
                break;

            case R.id.saturdayToggle:
                boolean isSaturdayChecked=((Switch)findViewById(R.id.saturdayToggle)).isChecked();
                if(isSaturdayChecked)
                    ((Switch) findViewById(R.id.everyDayToggle)).setChecked(!isSaturdayChecked);

                editFromReminderSaturday.setEnabled(isSaturdayChecked);
                editFromReminderSaturday.setText("");



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

                Utils.populateTimeField(fromDate,view.getContext());
                break;

            case R.id.reminderToTime:

                Utils.populateTimeField(toDate,view.getContext());
                break;

            case R.id.reminderFromTimeSunday:

                Utils.populateTimeField(editFromReminderSunday,view.getContext());
                break;

            case R.id.reminderToTimeSunday:

                Utils.populateTimeField(editToReminderSunday,view.getContext());
                break;

            case R.id.reminderFromTimeMonday:

                Utils.populateTimeField(editFromReminderMonday,view.getContext());
                break;

            case R.id.reminderToTimeMonday:

                Utils.populateTimeField(editToReminderMonday,view.getContext());
                break;

            case R.id.reminderFromTimeTuesday:

                Utils.populateTimeField(editFromReminderTuesday,view.getContext());
                break;

            case R.id.reminderToTimeTuesday:

                Utils.populateTimeField(editToReminderTuesday,view.getContext());
                break;

            case R.id.reminderFromTimeWednesday:

                Utils.populateTimeField(editFromReminderWednesday,view.getContext());
                break;

            case R.id.reminderToTimeWednesday:

                Utils.populateTimeField(editToReminderWednesday,view.getContext());
                break;

            case R.id.reminderFromTimeThursday:

                Utils.populateTimeField(editFromReminderThursday,view.getContext());
                break;

            case R.id.reminderToTimeThursday:

                Utils.populateTimeField(editToReminderThursday,view.getContext());
                break;

            case R.id.reminderFromTimeFriday:

                Utils.populateTimeField(editFromReminderFriday,view.getContext());
                break;

            case R.id.reminderToTimeFriday:

                Utils.populateTimeField(editToReminderFriday,view.getContext());
                break;

            case R.id.reminderFromTimeSaturday:

                Utils.populateTimeField(editToReminderSaturday,view.getContext());
                break;

            case R.id.reminderToTimeSaturday:

                Utils.populateTimeField(editFromReminderSaturday,view.getContext());
                break;
        }

    }

    public void persistReminder(View view){
        ReminderHelper reminderHelper=new ReminderHelper();
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());

        ReminderVO reminderVO=new ReminderVO();
        reminderVO.setReminderId(reminderId);

        reminderVO.setReminderName(editReminderName.getText().toString());
        reminderVO.setFromDate(fromDate.getText().toString());
        reminderVO.setToDate(toDate.getText().toString());

        reminderVO.setFromTime(editFromReminder.getText().toString());
        reminderVO.setToTime(editToReminder.getText().toString());

        reminderVO.setFromTimeSunday(editFromReminderSunday.getText().toString());
        reminderVO.setFromTimeMonday(editFromReminderMonday.getText().toString());
        reminderVO.setFromTimeTuesday(editFromReminderTuesday.getText().toString());
        reminderVO.setFromTimeWednesday(editFromReminderWednesday.getText().toString());
        reminderVO.setFromTimeThursday(editFromReminderThursday.getText().toString());
        reminderVO.setFromTimeFriday(editFromReminderFriday.getText().toString());
        reminderVO.setFromTimeSaturday(editFromReminderSaturday.getText().toString());

        reminderVO.setToTimeSunday(editToReminderSunday.getText().toString());
        reminderVO.setToTimeMonday(editToReminderMonday.getText().toString());
        reminderVO.setToTimeTuesday(editToReminderTuesday.getText().toString());
        reminderVO.setToTimeWednesday(editToReminderWednesday.getText().toString());
        reminderVO.setToTimeThursday(editToReminderThursday.getText().toString());
        reminderVO.setToTimeFriday(editToReminderFriday.getText().toString());
        reminderVO.setToTimeSaturday(editToReminderSaturday.getText().toString());

        reminderVO.setRepeatEveryHH(Utils.getValue(hhSpinner.getSelectedItem().toString(),reminderHHKey));
        reminderVO.setRepeatEveryMM(Utils.getValue(mmSpinner.getSelectedItem().toString(),reminderMinKey));
        reminderVO.setRepeatEverySS(Utils.getValue(ssSpinner.getSelectedItem().toString(),reminderSecKey));
        reminderVO.setInterval(Utils.getValue(ssReminderInterval.getSelectedItem().toString(),reminderSecKey));


        reminderHelper.persistReminder(personalAssistantDatabase,reminderVO);
        
        finish();
        startActivity(new Intent(this,ManageRemindersListActivity.class));
    }






}
