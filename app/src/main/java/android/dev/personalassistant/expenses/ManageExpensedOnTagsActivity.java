package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.enums.ExpenseTagCategory;
import android.dev.personalassistant.helpers.expenses.ExpensesTagHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.tags.TaggingUtility;
import android.dev.personalassistant.tags.TaggingView;
import android.dev.personalassistant.vo.expenses.ExpenseTagVO;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;

import static android.dev.personalassistant.utils.Constants.EXPENSED_ON_TAG_SHARED_PREFERENCE;

public class ManageExpensedOnTagsActivity extends AppCompatActivity {
    private EditText expensedOnElementalTagNameFieldObj;
    private GridLayout manageExpensedOnElementalTagsEditObj;
    private SharedPreferences mSharedPref;
    ExpensesTagHelper expensesTagHelper=new ExpensesTagHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expensed_for_tags);
        expensedOnElementalTagNameFieldObj =findViewById(R.id.expensedForElementalTagNameField);
        manageExpensedOnElementalTagsEditObj=findViewById(R.id.manageExpensedForElementalTagsEdit);
        mSharedPref=getSharedPreferences(EXPENSED_ON_TAG_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        expensedOnElementalTagNameFieldObj.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN) {
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.KEYCODE_ENTER:
                            String tag=expensedOnElementalTagNameFieldObj.getText().toString().trim();
                            if(tag.length()>0) {
                                addExpensedOnElementalTag(tag);
                            }
                    }
                }
                return false;
            }
        });

        fetchExpensedOnElementalTags();

    }






    private void fetchExpensedOnElementalTags(){
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());
        manageExpensedOnElementalTagsEditObj.removeAllViews();
        TaggingUtility taggingUtility=new TaggingUtility(this,manageExpensedOnElementalTagsEditObj);
        List<String> tags=new ArrayList<>();
        try {
            List<ExpenseTagVO> expenseTagVOs=expensesTagHelper.fetchAllExpenseTagVOs(personalAssistantDatabase);
            expenseTagVOs.stream().filter(tag -> (tag.getTagCategory()==ExpenseTagCategory.ExpensedOn.getVal())).forEach(tag -> {
                tags.add(tag.getTagName());
            });
        }catch (Exception ex){
            Log.e(ManageExpensedOnTagsActivity.class.getName(),ex.getLocalizedMessage());
        }
        taggingUtility.arrangeTagsOnGridLayout(this, manageExpensedOnElementalTagsEditObj,tags,mSharedPref);

    }


    private void addExpensedOnElementalTag(String tagName){
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());

        ExpenseTagVO expenseTagVO=new ExpenseTagVO();
        expenseTagVO.setTagCategory(ExpenseTagCategory.ExpensedOn.getVal());
        expenseTagVO.setTagName(tagName);
        expenseTagVO.setTagId(-1);
        expensesTagHelper.persistExpenseTag(personalAssistantDatabase,expenseTagVO);
        fetchExpensedOnElementalTags();

    }
}
