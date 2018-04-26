package android.dev.personalassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.dev.personalassistant.utils.TaggingUtility;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.HashSet;
import java.util.Set;

import static android.dev.personalassistant.Constants.DOCUMENTS_TAG_SHARED_PREFERENCE;
import static android.dev.personalassistant.Constants.SELECTED_TAG_KEYS;
import static android.dev.personalassistant.Constants.SMS_KEYWORDS;
import static android.dev.personalassistant.Constants.SMS_KEYWORDS_SET;

public class TrackElectronicExpensesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_electronic_expenses);

    }

    public void scanSMS(View view){
        final GridLayout mGridLayout=(GridLayout)findViewById(R.id.smsKeywordTagsGridLayout);
        final TaggingUtility taggingUtilitySelected=new TaggingUtility(this,mGridLayout);
        final SharedPreferences mSharedPref = this.getSharedPreferences(SMS_KEYWORDS, Context.MODE_PRIVATE);
        Set<String> smsKeywordSet=mSharedPref.getStringSet(SMS_KEYWORDS_SET,new HashSet<String>());
        SharedPreferences.Editor editor =mSharedPref.edit();

        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

        if (cursor.moveToFirst()) { // must check the result to prevent exception
            int i=0;
            do {
                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
                    smsKeywordSet.add(msgData);
                    editor.putString(msgData,msgData);
                    editor.commit();
                    i++;
                    if(i>10) break;
                }
                if(i>10)
                    break;
                // use msgData
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }

        editor.putStringSet(SMS_KEYWORDS_SET,smsKeywordSet);

        editor.commit();
        taggingUtilitySelected.populateTagView(mSharedPref, SMS_KEYWORDS_SET, null);

    }
}
