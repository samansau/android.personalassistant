package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.enums.ExpenseTagCategory;
import android.dev.personalassistant.helpers.expenses.ExpensesTagHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.tags.TaggingUtility;
import android.dev.personalassistant.vo.expenses.ExpenseTagVO;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import static android.dev.personalassistant.utils.Constants.EXPENSED_FOR_TAG_SHARED_PREFERENCE;

public class ManageExpensedForTagsActivity extends AppCompatActivity {
    private EditText expensedForElementalTagNameFieldObj;
    private GridLayout manageExpensedForElementalTagsEditObj;
    private SharedPreferences mSharedPref;
    ExpensesTagHelper expensesTagHelper=new ExpensesTagHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expensed_for_tags);
        expensedForElementalTagNameFieldObj =findViewById(R.id.expensedForElementalTagNameField);
        manageExpensedForElementalTagsEditObj =findViewById(R.id.manageExpensedForElementalTagsEdit);
        mSharedPref=getSharedPreferences(EXPENSED_FOR_TAG_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        expensedForElementalTagNameFieldObj.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN) {
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.KEYCODE_ENTER:
                            String tag= expensedForElementalTagNameFieldObj.getText().toString().trim();
                            if(tag.length()>0) {
                                addExpensedForElementalTag(tag);
                            }
                    }
                }
                return false;
            }
        });

        fetchExpensedForElementalTags();

    }






    private void fetchExpensedForElementalTags(){
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());
        manageExpensedForElementalTagsEditObj.removeAllViews();
        TaggingUtility taggingUtility=new TaggingUtility(this, manageExpensedForElementalTagsEditObj);
        List<String> tags=new ArrayList<>();
        try {
            List<ExpenseTagVO> expenseTagVOs=expensesTagHelper.fetchAllExpenseTagVOs(personalAssistantDatabase);
            expenseTagVOs.stream().filter(tag -> (tag.getTagCategory()==ExpenseTagCategory.ExpensedFor.getVal())).forEach(tag -> {
                tags.add(tag.getTagName());
            });
        }catch (Exception ex){
            Log.e(ManageExpensedForTagsActivity.class.getName(),ex.getLocalizedMessage());
        }
        taggingUtility.arrangeTagsOnGridLayout(this, manageExpensedForElementalTagsEditObj,tags,mSharedPref);

    }


    private void addExpensedForElementalTag(String tagName){
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());

        ExpenseTagVO expenseTagVO=new ExpenseTagVO();
        expenseTagVO.setTagCategory(ExpenseTagCategory.ExpensedFor.getVal());
        expenseTagVO.setTagName(tagName);
        expenseTagVO.setTagId(-1);
        expensesTagHelper.persistExpenseTag(personalAssistantDatabase,expenseTagVO);
        fetchExpensedForElementalTags();

    }
}
