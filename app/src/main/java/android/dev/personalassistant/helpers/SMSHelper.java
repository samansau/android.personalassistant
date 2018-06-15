package android.dev.personalassistant.helpers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by saurabh on 6/9/18.
 */

public class SMSHelper {

    public List<Map<String,String>> fetchSMSMessages(ContentResolver contentResolver){
        List<Map<String,String>> smsMessages=new ArrayList<>();
        Cursor cursor = contentResolver.query(Uri.parse("content://sms/inbox"), null, null, null, null);
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            do {
                String msgData = "";
                String [] columns=cursor.getColumnNames();
                Map<String,String> smsContent=new HashMap<>();
                for(String column:columns){
                    //System.out.println("column:"+column);
                    smsContent.put(column,cursor.getString(cursor.getColumnIndex(column)));
                }
                smsMessages.add(smsContent);
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        return smsMessages;
    }


}
