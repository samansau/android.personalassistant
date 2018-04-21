package android.dev.personalassistant.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

import static android.dev.personalassistant.Constants.DOCUMENTS_TAG_KEYS;
import static android.dev.personalassistant.Constants.DOCUMENTS_TAG_MAX_KEY;
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
    GridLayout mtableLayout;

    public TaggingUtility(Activity activity, GridLayout mtableLayout){
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

    public static void populateDocumentTagSharedPrefs(SharedPreferences mSharedPrefExpenses){

        SharedPreferences.Editor editor = mSharedPrefExpenses.edit();

        String [] keys={"1","2","3","4","5"};
        Set<String> mySet = new HashSet<String>(Arrays.asList(keys));

        editor.putString("1","Electricity");
        editor.putString("2","Car");
        editor.putString("3","Bills");
        editor.putString("4","Water");
        editor.putString("5","Insurance");

        editor.putStringSet(DOCUMENTS_TAG_KEYS,mySet);

        editor.putInt(DOCUMENTS_TAG_MAX_KEY,5);
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
                                  GridLayout mtableLayout,
                                  SharedPreferences mSharedPref,
                                  TextInputEditText editTag,String tagKey,boolean isNewRow){
                                        textView.setId(Integer.parseInt(key));
                                        tagRow.setGravity(center);
                                        textView.setTextSize(20);
                                        Random rnd = new Random();

                                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                                        //textView.setBackgroundColor(textView.getResources().getColor(android.R.color.holo_purple));
                                        textView.setTextColor(color);
                                        textView.setPadding(30,10,30,10);
                                        textView.setText(tag);
                                        textView.setBackgroundResource(R.drawable.rounded_border_layout);
                                        tagRow.addView(textView);
                                        if(isNewRow) {
                                            mtableLayout.addView(tagRow);
                                        }
                                        TaggingView tView=new TaggingView(mtableLayout,mSharedPref,editTag,tagKey);
                                        textView.setOnClickListener(tView);
                                        textView.setOnLongClickListener(tView);
                                }



    public void populateTagView(SharedPreferences mSharedPref,String tagKey,TextInputEditText inputTag){
        Set<String> tagKeys= mSharedPref.getStringSet(tagKey,null);
        int i=0;
        boolean isNewRow =true;
        TableRow tagRow=new TableRow(activity);
        int rowLength=0;
        Set<String> tags=new TreeSet<String>();
        Map<String,String> keysMap=new HashMap<>();
        TreeMap<Integer,Stack<String>> tagSizeMap=new TreeMap<>();
        for(String key:tagKeys){
            String tag=mSharedPref.getString(key,null);
            tags.add(tag);
            keysMap.put(tag,key);
            int tagSize=tag.length();
            Stack<String> tagStack=tagSizeMap.get(tagSize);
            if(tagStack==null) {
                tagStack = new Stack<>();
            }
            tagStack.add(tag);
            tagSizeMap.put(tagSize,tagStack);
        }
        int indx=0;
        int requiredSize=21;
        int allocatedSize=0;
        while(true){
            Stack<String> tagStack=null;
            while(!tagSizeMap.isEmpty() && requiredSize>=0) {
                tagStack = tagSizeMap.get(requiredSize);
                if(tagStack!=null) break;
                else {
                    requiredSize=requiredSize-1;
                }
            }

            if(tagSizeMap.isEmpty()) break;
            if(tagStack!=null && !tagStack.isEmpty()) {
                String tag = tagStack.pop();
                if (tagStack.isEmpty()) tagSizeMap.remove(requiredSize);
                TextView textView=new TextView(activity);
                TaggingUtility.addTagRows(tag,keysMap.get(tag),tagRow,textView,mtableLayout,mSharedPref,inputTag,EXPENSE_TAG_KEYS,isNewRow);
            }
            allocatedSize=allocatedSize+requiredSize;
            requiredSize=21-allocatedSize;
            if(requiredSize<=0){
                requiredSize=21;
                allocatedSize=0;
                isNewRow=true;
                tagRow=new TableRow(activity);
            }else{
                isNewRow=false;
            }


        }

//        for(String tag:sortedTags){
//            rowLength=rowLength+tag.length();
//            if(rowLength>20){
//                tagRow=new TableRow(activity);
//                isNewRow=true;
//                rowLength=tag.length();
//                i++;
//            }else if(i>0){
//                isNewRow=false;
//            }
//            TextView textView=new TextView(activity);
//            TaggingUtility.addTagRows(tag,keysMap.get(tag),tagRow,textView,mtableLayout,mSharedPref,inputTag,EXPENSE_TAG_KEYS,isNewRow);
//            i++;
//        }

    }


}
