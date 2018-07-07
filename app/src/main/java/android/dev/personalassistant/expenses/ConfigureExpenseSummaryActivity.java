package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.helpers.expenses.ExpensesHelper;
import android.dev.personalassistant.helpers.expenses.ExpensesTagHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.tags.TaggingUtility;
import android.dev.personalassistant.vo.expenses.ExpenseTagVO;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static android.dev.personalassistant.utils.Constants.EXPENSED_SMS_FILTER_SHARED_PREFERENCE;
import static android.dev.personalassistant.utils.Constants.EXPENSED_SMS_FILTER_SHARED_PREFERENCE_KEY;
import static android.dev.personalassistant.utils.Constants.EXPENSED_TAGS_SHARED_PREFERENCE_KEY;

public class ConfigureExpenseSummaryActivity extends AppCompatActivity {
    GridLayout mGridLayoutExpenseTagsSummary;
    SharedPreferences mSharedPrefSummaryTags;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPrefSummaryTags= this.getSharedPreferences(EXPENSED_TAGS_SHARED_PREFERENCE_KEY,Context.MODE_PRIVATE);
        setContentView(R.layout.activity_configure_expense_summary);
        mGridLayoutExpenseTagsSummary=findViewById(R.id.expenseTagsSummaryGridLayout);
        populateExpenseTagSummary();
    }

    private void populateExpenseTagSummary(){

        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(this);
        mGridLayoutExpenseTagsSummary.removeAllViews();
        TaggingUtility taggingUtility=new TaggingUtility(this,mGridLayoutExpenseTagsSummary);
        ExpensesTagHelper expensesTagHelper=new ExpensesTagHelper();
        try {
            List<ExpenseTagVO> expenseTagVOS=expensesTagHelper.fetchAllExpenseTagVOs(personalAssistantDatabase);
            List<String> tags=expenseTagVOS.stream().map(expenseTagVO -> expenseTagVO.getTagName()).collect(Collectors.toList());
            taggingUtility.arrangeTagsOnGridLayout(this, mGridLayoutExpenseTagsSummary,tags,mSharedPrefSummaryTags);
        }catch(Exception ex){
            ex.printStackTrace();
        }



    }




}
