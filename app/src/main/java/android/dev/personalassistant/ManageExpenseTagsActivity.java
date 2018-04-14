package android.dev.personalassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.dev.personalassistant.utils.TaggingInput;
import android.dev.personalassistant.utils.TaggingUtility;
import android.dev.personalassistant.utils.TaggingView;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import static android.dev.personalassistant.R.color.inputTextColor;
import static android.support.design.R.id.center;

public class ManageExpenseTagsActivity extends BaseActivity implements Constants{
    int mExpensesTagMaxKey;
    TableLayout mtableLayout;
    int mSelectedTagKey=-1;
    SharedPreferences mSharedPref = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expense_tags);

        final TextInputEditText editExpenseTag=(TextInputEditText)findViewById(R.id.editExpenseTags);
        mSharedPref = this.getSharedPreferences(EXPENSE_TAG_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        TaggingInput taggingInput=new TaggingInput();
        editExpenseTag.setOnKeyListener(taggingInput);
        taggingInput.setmSharedPref(mSharedPref);
        taggingInput.setInputTag(editExpenseTag);
//        editExpenseTag.setOnKeyListener(new View.OnKeyListener() {
//            @Override
//            public boolean onKey(View view, int i, KeyEvent keyEvent) {
//                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN) {
//                    switch (keyEvent.getKeyCode()) {
//                        case KeyEvent.KEYCODE_ENTER:
//                            String tag=editExpenseTag.getText().toString().trim();
//                            if(tag.length()>0) {
//                                //persistTagChanges(tag);
//                                mSelectedTagKey=mSharedPref.getInt(SELECTED_TAG_KEY, -1);
//                                TaggingUtility.persistTagChanges(tag,mSharedPref,mSelectedTagKey);
//                                recreate();
//                                SharedPreferences.Editor editor=mSharedPref.edit();
//                                editor.putInt(SELECTED_TAG_KEY,-1);
//                                editor.commit();
//                            }
//                            return true;
//                    }
//                }
//                return false;
//            }
//        });
        mExpensesTagMaxKey= mSharedPref.getInt(EXPENSE_TAG_MAX_KEY, 0);
        if(mExpensesTagMaxKey==0){
            TaggingUtility.populateExpensesTagSharedPrefs(mSharedPref);

        }
        populateExpensesTagView();

    }



    private void populateExpensesTagView(){
        mtableLayout=(TableLayout)findViewById(R.id.listExpenseTags);

        Set<String> tagKeys= mSharedPref.getStringSet(EXPENSE_TAG_KEYS,null);
        for(String key:tagKeys){
            String tag=mSharedPref.getString(key,null);
            //addTagRows(tag,key);
            TableRow tagRow=new TableRow(this);
            TextView textView=new TextView(this);
            TextInputEditText editExpenseTag=(TextInputEditText)findViewById(R.id.editExpenseTags);
            TaggingUtility.addTagRows(tag,key,tagRow,textView,mtableLayout,mSharedPref,editExpenseTag);
        }

    }



    private void addTagRows(String tag,String key){

        TableRow tagRow=new TableRow(this);
        tagRow.setId(Integer.parseInt(key));
        tagRow.setGravity(center);
        TextView textView=new TextView(this);
        textView.setTextSize(20);
        textView.setText(tag);
        tagRow.addView(textView);
        mtableLayout.addView(tagRow);
//        tagRow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                for(int i=0;i<mtableLayout.getChildCount();i++){
//                    mtableLayout.getChildAt(i).setBackgroundColor(mtableLayout.getSolidColor());
//                }
//                final TextInputEditText editExpenseTage=(TextInputEditText)findViewById(R.id.editExpenseTags);
//                TableRow tagClicked=(TableRow)view;
//                tagClicked.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
//
//                if(mSelectedTagKey==tagClicked.getId()){
//                    tagClicked.setBackgroundColor(mtableLayout.getSolidColor());
//                    mSelectedTagKey=-1;
//                }else{
//                    mSelectedTagKey=tagClicked.getId();
//                    editExpenseTage.setText(((TextView)tagClicked.getChildAt(0)).getText());
//                }
//
//
//            }
//        });
        TaggingView tView=new TaggingView();
        TextInputEditText editExpenseTag=(TextInputEditText)findViewById(R.id.editExpenseTags);
        tView.setEditTag(editExpenseTag);
        tView.setMtableLayout(mtableLayout);
        tView.setmSharedPref(mSharedPref);
        tagRow.setOnClickListener(tView);
        tagRow.setOnLongClickListener(tView);


//        tagRow.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                mSelectedTagKey=mSharedPref.getInt(SELECTED_TAG_KEY, -1);
//                if(mSelectedTagKey!=-1) {
//                    SharedPreferences.Editor editor = mSharedPref.edit();
//                    editor.remove(mSelectedTagKey + "").commit();
//                    Set<String> tagKeys = mSharedPref.getStringSet(EXPENSE_TAG_KEYS, null);
//                    try {
//                        tagKeys.remove(mSelectedTagKey + "");
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
//                    editor.putStringSet(EXPENSE_TAG_KEYS, tagKeys);
//                    populateExpensesTagView();
//                    recreate();
//                    return true;
//                }else{
//                    return false;
//                }
//            }
//        });


    }


}
