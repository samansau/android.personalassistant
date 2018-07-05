package android.dev.personalassistant.expenses;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.helpers.expenses.ExpensesHelper;
import android.dev.personalassistant.helpers.expenses.ExpensesTagHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.main.BaseActivity;
import android.dev.personalassistant.main.MainActivity;
import android.dev.personalassistant.tags.TaggingView;
import android.dev.personalassistant.utils.Constants;
import android.dev.personalassistant.tags.TaggingUtility;
import android.dev.personalassistant.utils.Keys;
import android.dev.personalassistant.utils.Utils;
import android.dev.personalassistant.vo.expenses.ExpenseTagVO;
import android.dev.personalassistant.vo.expenses.ExpenseVO;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TableRow;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static android.dev.personalassistant.utils.Keys.expenseAmount;
import static android.dev.personalassistant.utils.Keys.expenseDate;
import static android.dev.personalassistant.utils.Keys.expenseDescription;
import static android.dev.personalassistant.utils.Keys.expenseForTags;
import static android.dev.personalassistant.utils.Keys.expenseOnTags;

public class AddEditExpensesActivity extends BaseActivity implements Constants {
    SharedPreferences mSharedPrefExpensedOn= null;
    SharedPreferences mSharedPrefExpensedFor=null;
    Activity context;
    GridLayout mGridLayoutExpensedForTags;
    GridLayout mGridLayoutExpensedOnTags;
    EditText expenseAmountObj;
    EditText expenseBriefDescriptionObj;
    DatePicker expenseDateObj;
    int expenseId=-1;


    public  void openManageExpenseTags(View view){
        Intent intent=new Intent(this,ManageExpensedForTagsActivity.class);
        startActivity(intent);
    }

    public void openManageExpensedOnTags(View view){
        Intent intent=new Intent(this,ManageExpensedOnTagsActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedInstanceState=new Bundle();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_expenses);
        mSharedPrefExpensedFor= this.getSharedPreferences(EXPENSED_FOR_TAG_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        mSharedPrefExpensedOn= this.getSharedPreferences(EXPENSED_ON_TAG_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);


        initDrawer();
        createExpenseToolBar();
        context=this;

        mGridLayoutExpensedForTags=(GridLayout)findViewById(R.id.selectedExpensedForTags);
        mGridLayoutExpensedOnTags=(GridLayout)findViewById(R.id.selectedExpensedOnTags);
        expenseAmountObj=findViewById(R.id.expenseAmount);
        expenseBriefDescriptionObj=findViewById(R.id.expenseBriefDescription);
        expenseDateObj=findViewById(R.id.inputExpenseDate);

        mSharedPrefExpensedFor.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(SELECTED_TAG_KEY.equals(key)) {
                    mGridLayoutExpensedForTags.removeAllViews();
                    TaggingUtility taggingUtility=new TaggingUtility(context,mGridLayoutExpensedForTags);
                    Set<String> tagSet=sharedPreferences.getStringSet(SELECTED_TAG_KEY,null);
                    List<String> tags=new ArrayList<>(tagSet);
                    taggingUtility.arrangeTagsOnGridLayout(context, mGridLayoutExpensedForTags,tags,mSharedPrefExpensedFor);
                }
            }
        });


        mSharedPrefExpensedOn.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(SELECTED_TAG_KEY.equals(key)) {
                    mGridLayoutExpensedOnTags.removeAllViews();
                    TaggingUtility taggingUtility=new TaggingUtility(context,mGridLayoutExpensedOnTags);
                    Set<String> tagSet=sharedPreferences.getStringSet(SELECTED_TAG_KEY,null);
                    List<String> tags=new ArrayList<>(tagSet);
                    taggingUtility.arrangeTagsOnGridLayout(context, mGridLayoutExpensedOnTags,tags,mSharedPrefExpensedOn);
                }
            }


        });


        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            expenseId=bundle.getInt(Keys.expenseId);
            expenseAmountObj.setText(bundle.getDouble(expenseAmount)+"");
            expenseBriefDescriptionObj.setText(bundle.getString(expenseDescription));
            Date date=new Date(bundle.getLong(expenseDate));
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            expenseDateObj.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE));

            List<String> forTags=bundle.getStringArrayList(expenseForTags);
            TaggingUtility taggingUtilityForTags=new TaggingUtility(context,mGridLayoutExpensedForTags);
            taggingUtilityForTags.arrangeTagsOnGridLayout(context, mGridLayoutExpensedForTags,forTags,mSharedPrefExpensedFor);

            List<String> onTags=bundle.getStringArrayList(expenseOnTags);
            TaggingUtility taggingUtilityOnTags=new TaggingUtility(context,mGridLayoutExpensedOnTags);
            taggingUtilityOnTags.arrangeTagsOnGridLayout(context, mGridLayoutExpensedOnTags,onTags,mSharedPrefExpensedOn);
        }


    }


    public void saveExpense(View view){
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(getApplicationContext());


        ExpenseVO expenseVO=new ExpenseVO();
        expenseVO.setExpenseAmount(Double.parseDouble(expenseAmountObj.getText().toString()));
        int expensedOnTagSize=mGridLayoutExpensedOnTags.getChildCount();
        int expensedForTagSize=mGridLayoutExpensedForTags.getChildCount();

        List<String> expensedOnList=new ArrayList<>();
        for(int i=0;i<expensedOnTagSize;i++){
            TableRow tableRow=(TableRow) mGridLayoutExpensedOnTags.getChildAt(i);
            if(tableRow==null) continue;
            int colCount=tableRow.getChildCount();
            for(int j=0;j<colCount;j++) {
                TextView textView=(TextView)tableRow.getChildAt(j);
                expensedOnList.add(textView.getText().toString());
            }
        }
        expenseVO.setExpensedOnTags(expensedOnList);

        List<String> expensedForList=new ArrayList<>();
        for(int i=0;i<expensedForTagSize;i++){
            TableRow tableRow=(TableRow) mGridLayoutExpensedForTags.getChildAt(i);
            if(tableRow == null) continue;
            int colCount=tableRow.getChildCount();
            for(int j=0;j<colCount;j++) {
                TextView textView=(TextView)tableRow.getChildAt(j);
                expensedForList.add(textView.getText().toString());
            }
        }
        expenseVO.setExpensedForTags(expensedForList);

        expenseVO.setBriefDescription(expenseBriefDescriptionObj.getText().toString());
        expenseVO.setExpenseDate(Utils.getDateFromDatePicker(expenseDateObj).getTime());
        expenseVO.setExpenseId(expenseId);

        expenseVO.setManuallyEntered(true);

        ExpensesHelper expensesHelper=new ExpensesHelper();
        expensesHelper.persistExpense(personalAssistantDatabase,expenseVO);
        finish();
        startActivity(new Intent(this,MainActivity.class));

   }



}
