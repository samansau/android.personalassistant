package android.dev.personalassistant.tags;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.widget.GridLayout;
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

import static android.dev.personalassistant.utils.Constants.DOCUMENTS_TAG_KEYS;
import static android.dev.personalassistant.utils.Constants.DOCUMENTS_TAG_MAX_KEY;
import static android.dev.personalassistant.utils.Constants.EXPENSE_TAG_KEYS;
import static android.dev.personalassistant.utils.Constants.EXPENSE_TAG_MAX_KEY;
import static android.dev.personalassistant.utils.Constants.SELECTED_TAG_KEY;
import static android.dev.personalassistant.utils.Constants.SELECTED_TAG_KEYS;
import static android.support.design.R.id.center;

/**
 * Created by saurabh on 4/10/18.
 */

public class TaggingUtility {

    Activity activity;
    GridLayout mGridLayout;

    public TaggingUtility(Activity activity, GridLayout mGridLayout){
        this.activity=activity;
        this.mGridLayout = mGridLayout;
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
                                  TableRow tagRow,
                                  TextView textView,
                                  GridLayout mGridLayout,
                                  SharedPreferences mSharedPref
                                  ,boolean isNewRow){
        tagRow.setGravity(center);
        textView.setTextSize(20);

        textView.setTextColor(Color.BLACK);
        textView.setPadding(30,10,30,10);
        textView.setText(tag);

        if(mSharedPref!=null) {
            if (!mSharedPref.getStringSet(SELECTED_TAG_KEY, new HashSet<>()).contains(tag)) {
                textView.setBackgroundResource(R.drawable.rounded_border_layout);
            } else {
                textView.setBackgroundResource(R.drawable.rounded_border_layout_selected);
            }
        }
        tagRow.addView(textView);
        if(isNewRow) {
            mGridLayout.addView(tagRow);
        }
        TaggingView taggingView=new TaggingView(mSharedPref);
        textView.setOnClickListener(taggingView);
        textView.setOnLongClickListener(taggingView);
    }


    public static void addTagRows(String tag,
                                  String key,
                                  TableRow tagRow,
                                  TextView textView,
                                  GridLayout mGridLayout,
                                  SharedPreferences mSharedPref,
                                  TextInputEditText editTag,String tagKey,boolean isNewRow){
                                        textView.setId(Integer.parseInt(key));
                                        tagRow.setGravity(center);
                                        textView.setTextSize(20);
                                        Random rnd = new Random();
                                        Set<String> selectedTagKeys=mSharedPref.getStringSet(SELECTED_TAG_KEYS,new HashSet<String>());
                                        //int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                                        //textView.setBackgroundColor(textView.getResources().getColor(android.R.color.holo_purple));
                                        textView.setTextColor(Color.BLACK);
                                        textView.setPadding(30,10,30,10);
                                        textView.setText(tag);

                                        if(selectedTagKeys.contains(key)){
                                            textView.setBackgroundResource(R.drawable.rounded_border_layout_selected);
                                        }else {
                                            textView.setBackgroundResource(R.drawable.rounded_border_layout);
                                        }
                                        tagRow.addView(textView);
                                        if(isNewRow) {
                                            mGridLayout.addView(tagRow);
                                        }
                                        TaggingView tView=new TaggingView(mSharedPref);
                                        if((mGridLayout.getId()==R.id.listDocumentsTags )|| (mGridLayout.getId()==R.id.listExpenseTags)) {
                                            textView.setOnClickListener(tView);
                                            textView.setOnLongClickListener(tView);
                                        }
                                }




    public void populateTagView(SharedPreferences mSharedPref,String tagKey,TextInputEditText inputTag){
        Set<String> tagKeys= mSharedPref.getStringSet(tagKey,new HashSet<String>());
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
                TaggingUtility.addTagRows(tag,keysMap.get(tag),tagRow,textView, mGridLayout,mSharedPref,inputTag,EXPENSE_TAG_KEYS,isNewRow);
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
    }

    public List<List<String>> getTagsArrangedForGridView(List<String> tags, int maxRowLength){
        List<List<String>> arrangedTags=new ArrayList<>();
        Stack<String> tagStack=new Stack<>();
        tagStack.addAll(tags);
        Collections.sort(tagStack, new Comparator<String>() {
            @Override
            public int compare(String elem1, String elem2) {
                return (elem1.length()-elem2.length());
            }
        });
        int allocatedLength=0;
        arrangedTags.add(new ArrayList<>());
        List<String> leftOver=new ArrayList<>();
        while(true){
            String tag=tagStack.pop();
            int tagLength=tag.length();
            int remainingLength=maxRowLength-allocatedLength;
            if(remainingLength>=tagLength){
                List<String> row=arrangedTags.get(arrangedTags.size()-1);
                row.add(tag);
                allocatedLength=allocatedLength+tagLength;
            }else{
                leftOver.add(tag);
            }
            if(tagStack.size()==0){// One iteration complete
                Collections.reverse(leftOver);
                tagStack.addAll(leftOver);
                leftOver.clear();
                allocatedLength=0;
                arrangedTags.add(new ArrayList<>());
            }
            if(tagStack.size()==0 && leftOver.size()==0) break;
        }

        return arrangedTags;

    }

    public void arrangeTagsOnGridLayout(Activity activity,GridLayout gridLayout,List<String> tags,SharedPreferences mSharedPref) {
        if (tags != null && !tags.isEmpty()) {
            List<List<String>> arrangedTags = getTagsArrangedForGridView(tags, 15);
            boolean isNewRow = true;
            TableRow tableRow = null;
            for (List<String> rows : arrangedTags) {
                for (String tag : rows) {
                    TextView textView = new TextView(activity);
                    textView.setText(tag);
                    if (isNewRow)
                        tableRow = new TableRow(activity);
                    addTagRows(tag, tableRow, textView, gridLayout,mSharedPref, isNewRow);
                    isNewRow = false;
                }
                isNewRow = true;
            }
        }
    }
}
