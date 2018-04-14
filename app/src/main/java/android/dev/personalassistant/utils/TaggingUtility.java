package android.dev.personalassistant.utils;

import android.content.SharedPreferences;
import android.dev.personalassistant.R;
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

/**
 * Created by saurabh on 4/10/18.
 */

public class TaggingUtility {


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

    public static void persistTagChanges(String text,SharedPreferences mSharedPref,int mSelectedTagKey){

        SharedPreferences.Editor editor=mSharedPref.edit();
        int key;
        if(mSelectedTagKey==-1){//Insert
            key=mSharedPref.getInt(EXPENSE_TAG_MAX_KEY, 0);
            key=key+1;
            Set<String> tagKeys=mSharedPref.getStringSet(EXPENSE_TAG_KEYS,null);
            tagKeys.add(String.valueOf(key));
            editor.putStringSet(EXPENSE_TAG_KEYS,tagKeys);
            editor.putInt(EXPENSE_TAG_MAX_KEY,key);

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
                                  TextInputEditText editExpenseTag){
                                        tagRow.setId(Integer.parseInt(key));
                                        tagRow.setGravity(center);
                                        textView.setTextSize(20);
                                        textView.setText(tag);
                                        tagRow.addView(textView);
                                        mtableLayout.addView(tagRow);
                                        TaggingView tView=new TaggingView();
                                        tView.setEditTag(editExpenseTag);
                                        tView.setMtableLayout(mtableLayout);
                                        tView.setmSharedPref(mSharedPref);
                                        tagRow.setOnClickListener(tView);
                                        tagRow.setOnLongClickListener(tView);
                                }






}
