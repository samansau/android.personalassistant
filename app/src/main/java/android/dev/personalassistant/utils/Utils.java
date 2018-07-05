package android.dev.personalassistant.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.dev.personalassistant.main.MainActivity;
import android.dev.personalassistant.vo.expenses.ExpenseVO;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static android.dev.personalassistant.utils.Keys.expenseAllTags;
import static android.dev.personalassistant.utils.Keys.expenseAmount;
import static android.dev.personalassistant.utils.Keys.expenseDate;
import static android.dev.personalassistant.utils.Keys.expenseDescription;



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

    public static ExpenseVO extractExpenseFromMessage(String expenseMessage){
        String []tokens={"Rs.","INR","₹"};
        int indx=-1;
        int indxIndx=0;
        for(String token:tokens) {
             indx= expenseMessage.indexOf(token);
             if (indx > -1) break;
            indxIndx++;
        }
        Log.d("indx ",indx+"");
        ExpenseVO expenseVO=new ExpenseVO();
        expenseVO.setExpensedForTags(new ArrayList<>());
        expenseVO.setExpensedOnTags(new ArrayList<>());
        expenseVO.setBriefDescription(expenseMessage);
        if(indx>-1){
            String amountStartMsg=expenseMessage.substring(indx+tokens[indxIndx].length());
            Log.d("amountStartMsg ",amountStartMsg);
            amountStartMsg=amountStartMsg.trim();
            String amount=amountStartMsg.substring(0,amountStartMsg.indexOf(" "));


            Log.d("amount ",amount);
            try {
                amount=amount.trim();
                amount=amount.replace(",","");
                double expenseAmount = Double.parseDouble(amount);
                expenseVO.setExpenseAmount(expenseAmount);
            }catch(Exception ex){
                ex.printStackTrace();
            }

        }

        return expenseVO;
    }

    public static Date getDateFromDatePicker(DatePicker datePicker){
        Calendar cal=Calendar.getInstance();
        cal.set(datePicker.getYear(),datePicker.getMonth(),datePicker.getDayOfMonth());
        return cal.getTime();

    }


    public static void populateListOf4Items(List list) {
        HashMap map = new HashMap();
        map.put(expenseAllTags, "Education");
        map.put(expenseDate, "2018-06-05");
        map.put(expenseAmount, "12345");

        list.add(map);

        map = new HashMap();
        map.put(expenseAllTags, "Health");
        map.put(expenseDate, "2018-06-02");
        map.put(expenseAmount, "1234");
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

    public static String padZeroes(int val,int totalSize){
        String valStr=val+"";
        for(int i=valStr.length();i<totalSize;i++){
            valStr="0"+valStr;
        }
        return valStr;
    }


}
