package android.dev.personalassistant.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.dev.personalassistant.main.MainActivity;
import android.support.v7.app.AlertDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static android.dev.personalassistant.utils.Keys.expenseAmount;
import static android.dev.personalassistant.utils.Keys.expenseDate;
import static android.dev.personalassistant.utils.Keys.expenseDescription;
import static android.dev.personalassistant.utils.Keys.expenseTags;

/**
 * Created by saurabh on 5/6/18.
 */

public class Utils {
    public static void populateList(List list) {
        HashMap map = new HashMap();
        map.put("rank", "1");
        map.put("model", "Samsung Galaxy Nexus");
        list.add(map);

        map = new HashMap();
        map.put("rank", "2");
        map.put("model", "Samsung Epic Touch 4G");
        list.add(map);

        map = new HashMap();
        map.put("rank", "3");
        map.put("model", "Samsung Epic Touch 5G");
        list.add(map);

        map = new HashMap();
        map.put("rank", "4");
        map.put("model", "Samsung Epic Touch 6G");
        list.add(map);

        map = new HashMap();
        map.put("rank", "5");
        map.put("model", "Samsung Epic Touch 6G");
        list.add(map);

        map = new HashMap();
        map.put("rank", "6");
        map.put("model", "Samsung Epic Touch 6G");
        list.add(map);

    }
    public static void populateListOf4Items(List list) {
        HashMap map = new HashMap();
        map.put(expenseTags, "Education,Kids");
        map.put(expenseDate, "2018-06-05");
        map.put(expenseAmount, "12345");
        map.put(expenseDescription, "xyz");

        list.add(map);

        map = new HashMap();
        map.put(expenseTags, "Health");
        map.put(expenseDate, "2018-06-02");
        map.put(expenseAmount, "1234");
        map.put(expenseDescription, "xyz");
        list.add(map);



    }


    public static void showDialog(String text,Activity activity){
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
        dialog.setCancelable(false);
        dialog.setTitle("Dialog on Android");
        dialog.setMessage(text);
        dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
            }
        })
                .setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Action for "Cancel".
                    }
                });

        final AlertDialog alert = dialog.create();
        alert.show();

    }

    public static void populateDateField(EditText fieldToPopulate,Context context){
        int mYear, mMonth, mDay;
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        fieldToPopulate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();

        //return fieldToPopulate;
    }




    public static void populateTimeField(EditText fieldToPopulate,Context context){
        int mHour, mMinute;
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        fieldToPopulate.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();

    }

    public static int getValue(String val,String removeExtraString){
        String intPart=val.substring(0,val.indexOf(removeExtraString));
        return Integer.parseInt(intPart.trim());

    }


}
