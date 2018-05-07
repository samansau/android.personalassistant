package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.main.BaseActivity;
import android.dev.personalassistant.utils.Constants;
import android.dev.personalassistant.R;
import android.dev.personalassistant.tags.TaggingInput;
import android.dev.personalassistant.tags.TaggingUtility;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.widget.GridLayout;

public class ManageExpenseTagsActivity extends BaseActivity implements Constants {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expense_tags);
        final TextInputEditText editExpenseTag=(TextInputEditText)findViewById(R.id.editExpenseTags);
        SharedPreferences mSharedPref = this.getSharedPreferences(EXPENSE_TAG_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        TaggingInput taggingInput=new TaggingInput(this,mSharedPref,editExpenseTag,EXPENSE_TAG_KEYS,EXPENSE_TAG_MAX_KEY);
        editExpenseTag.setOnKeyListener(taggingInput);
        int mExpensesTagMaxKey= mSharedPref.getInt(EXPENSE_TAG_MAX_KEY, 0);
        if(mExpensesTagMaxKey==0){
            TaggingUtility.populateExpensesTagSharedPrefs(mSharedPref);
        }
        GridLayout mtableLayout=(GridLayout)findViewById(R.id.listExpenseTags);
        TaggingUtility taggingUtility=new TaggingUtility(this,mtableLayout);
        taggingUtility.populateTagView(mSharedPref,EXPENSE_TAG_KEYS,editExpenseTag);
    }
}