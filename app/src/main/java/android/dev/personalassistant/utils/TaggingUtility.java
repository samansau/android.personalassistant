package android.dev.personalassistant.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static android.dev.personalassistant.Constants.EXPENSE_TAG_KEYS;
import static android.dev.personalassistant.Constants.EXPENSE_TAG_MAX_KEY;
import static android.dev.personalassistant.Constants.SELECTED_TAG_KEY;
import static android.support.design.R.id.center;
import android.widget.TableLayout;
/**
 * Created by saurabh on 4/10/18.
 */

public class TaggingUtility {

    Activity activity;
    TableLayout mtableLayout;

    public TaggingUtility(Activity activity, TableLayout mtableLayout){
        this.activity=activity;
        this.mtableLayout=mtableLayout;
    }


    public static void populateExpensesTagSharedPrefs(SharedPreferences mSharedPrefExpenses){

        SharedPreferences.Editor editor = mSharedPrefExpenses.edit();

        String [] keys={"1","2","3","4","5"};
        Set<String> mySet = new HashSet<String>(Arrays.asList(keys));

        editor.putString("1","Maintenance");
        editor.putString("2","Health");
        editor.putString("3","Bills");
        editor.putString("4","Kids");
        editor.putString("5","Wife");

        editor.putStringSet(EXPENSE_TAG_KEYS,mySet);

        editor.putInt(EXPENSE_TAG_MAX_KEY,5);
        editor.commit();
    }

    public static void persistTagChanges(String text,SharedPreferences mSharedPref,int mSelectedTagKey,String tagKey,String tagMaxKey){

        SharedPreferences.Editor editor=mSharedPref.edit();
        int key;
        if(mSelectedTagKey==-1){//Insert
            key=mSharedPref.getInt(tagMaxKey, 0);
            key=key+1;
            Set<String> tagKeys=mSharedPref.getStringSet(tagKey,null);
            tagKeys.add(String.valueOf(key));
            editor.putStringSet(tagKey,tagKeys);
            editor.putInt(tagMaxKey,key);

        }else{//Update
            key=mSelectedTagKey;
        }
        editor.putString(String.valueOf(key),text);
        editor.commit();


    }



    public static void addTagRows(String tag,
                                  String key,
                                  TableRow tagRow,
                                  TextView textView,
                                  TableLayout mtableLayout,
                                  SharedPreferences mSharedPref,
                                  TextInputEditText editTag,String tagKey){
                                        tagRow.setId(Integer.parseInt(key));
                                        tagRow.setGravity(center);
                                        textView.setTextSize(20);
                                        textView.setText(tag);
                                        tagRow.addView(textView);
                                        mtableLayout.addView(tagRow);
                                        TaggingView tView=new TaggingView(mtableLayout,mSharedPref,editTag,tagKey);
                                        tagRow.setOnClickListener(tView);
                                        tagRow.setOnLongClickListener(tView);
                                }



    public void populateExpensesTagView(SharedPreferences mSharedPref,String tagKey,TextInputEditText inputTag){
        Set<String> tagKeys= mSharedPref.getStringSet(tagKey,null);
        for(String key:tagKeys){
            String tag=mSharedPref.getString(key,null);
            //addTagRows(tag,key);
            TableRow tagRow=new TableRow(activity);
            TextView textView=new TextView(activity);
            TaggingUtility.addTagRows(tag,key,tagRow,textView,mtableLayout,mSharedPref,inputTag,EXPENSE_TAG_KEYS);
        }

    }


}
