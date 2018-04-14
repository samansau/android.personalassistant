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
        TaggingInput taggingInput=new TaggingInput(this,mSharedPref,editExpenseTag);
        editExpenseTag.setOnKeyListener(taggingInput);

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




}
