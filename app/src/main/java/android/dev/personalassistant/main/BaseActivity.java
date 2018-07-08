package android.dev.personalassistant.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.dev.personalassistant.MyInformationActivity;
import android.dev.personalassistant.R;
import android.dev.personalassistant.dao.PersonalAssistantDatabase;
import android.dev.personalassistant.expenses.AddEditExpensesActivity;
import android.dev.personalassistant.expenses.ConfigureExpenseSummaryActivity;
import android.dev.personalassistant.expenses.ConfigureScanSMSActivity;
import android.dev.personalassistant.helpers.expenses.ExpensesHelper;
import android.dev.personalassistant.helpers.kym.DatabaseHelper;
import android.dev.personalassistant.investments.InvestmentsActivity;
import android.dev.personalassistant.reminders.ManageRemindersListActivity;
import android.dev.personalassistant.tabs.TabAdapter;
import android.dev.personalassistant.expenses.ExpenseTabFragment;
import android.dev.personalassistant.tabs.TabFragment;
import android.dev.personalassistant.documents.ShowDocumentsListActivity;
import android.dev.personalassistant.expenses.ManageExpenseTagsActivity;
import android.dev.personalassistant.expenses.TrackElectronicExpensesActivity;
import android.dev.personalassistant.kym.KnowYourMasterActivity;
import android.dev.personalassistant.utils.Utils;
import android.dev.personalassistant.vo.expenses.ExpenseVO;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static android.dev.personalassistant.utils.Constants.EXPENSED_SMS_FILTER_SHARED_PREFERENCE;
import static android.dev.personalassistant.utils.Constants.EXPENSED_SMS_FILTER_SHARED_PREFERENCE_KEY;
import static android.dev.personalassistant.utils.Constants.EXPENSED_TAGS_SHARED_PREFERENCE_KEY;
import static android.dev.personalassistant.utils.Constants.SELECTED_TAG_KEY;
import static android.dev.personalassistant.utils.Constants.SMS_READ_PERMISSION;
import static android.dev.personalassistant.utils.Keys.expenseAllTags;
import static android.dev.personalassistant.utils.Keys.expenseAmount;
import static android.dev.personalassistant.utils.Keys.expenseDate;
import static android.dev.personalassistant.utils.Keys.expenseId;

/**
 * Created by saurabh on 3/31/18.
 */


public class BaseActivity  extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    protected ActionBarDrawerToggle mActionBarDrawerToggle;
    protected DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    private RadioButton expenseSummaryFilterOnOption;
    private RadioButton expenseSummaryGroupOnOption;
    private RadioButton expenseSummaryDailyOption;
    private RadioButton expenseSummaryWeeklyOption;
    private RadioButton expenseSummaryMonthlyOption;
    private RadioButton expenseSummaryQuarterlyOption;
    private RadioButton expenseSummaryAnnuallyOption;
    private RadioGroup expenseSummaryRadioGroup1;
    private RadioGroup expenseSummaryRadioGroup2;
    private ListView summaryExpensesListView;
    private ListAdapter summaryExpensesAdapter;
    private TextView expenseSummaryTotalObj;
    private SharedPreferences mSharedPrefSummaryTags;
    private ToggleButton expenseSummarySelectToggleTagsObj;

    static final ArrayList<Map<String,String>> summaryExpensesList  =
            new ArrayList<Map<String,String>>();


    private void init(){
        expenseSummaryFilterOnOption=findViewById(R.id.expenseSummaryFilterOn);
        expenseSummaryGroupOnOption=findViewById(R.id.expenseSummaryGroupOn);
        expenseSummaryQuarterlyOption=findViewById(R.id.expenseSummaryWeeklyOption);
        expenseSummaryWeeklyOption=findViewById(R.id.expenseSummaryMonthlyOption);
        expenseSummaryMonthlyOption=findViewById(R.id.expenseSummaryQuarterlyOption);
        expenseSummaryAnnuallyOption=findViewById(R.id.expenseSummaryAnnuallyOption);
        expenseSummaryRadioGroup1 =findViewById(R.id.expenseSummaryRadioGroup1);
        expenseSummaryRadioGroup2=findViewById(R.id.expenseSummaryRadioGroup2);
        expenseSummaryTotalObj=findViewById(R.id.expenseSummaryTotal);
        mSharedPrefSummaryTags = this.getSharedPreferences(EXPENSED_TAGS_SHARED_PREFERENCE_KEY,Context.MODE_PRIVATE);
        expenseSummarySelectToggleTagsObj=findViewById(R.id.expenseSummarySelectToggleTags);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initDrawer();
        createExpenseToolBar();
        populateFinancialTransactionsTabs();




    }

    protected void initDrawer(){
        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);

        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name);

        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mNavigationView=(NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.financial_transactions:
                        openFinancialTransactions();
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.track_electronic_expenses:
                        openTrackElectronicExpenses();
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.my_information:
                        openMyInformation();
                        mDrawerLayout.closeDrawers();
                        return true;

                    case R.id.manage_documents:
                        openManageDocuments();
                        mDrawerLayout.closeDrawers();
                        return true;

                    case R.id.kym:
                        openKym();
                        mDrawerLayout.closeDrawers();
                        return true;

                    case R.id.my_investments:
                        openMyInvestments();
                        mDrawerLayout.closeDrawers();
                        return true;

                    case R.id.reminders:
                        openReminders();
                        mDrawerLayout.closeDrawers();
                        return true;


                }
                return true;
            }
        });




    }

    protected void createExpenseToolBar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.manage_expenses);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.expense_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.expenseTag:
                //Toast.makeText(this, "You Clicked on Add", Toast.LENGTH_LONG).show();
                openManageExpenseTags();
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    protected void openManageExpenseTags(){
        Intent intent=new Intent(this,ManageExpenseTagsActivity.class);
        startActivity(intent);
    }


    protected void openMyInformation(){
        Intent intent=new Intent(this,MyInformationActivity.class);
        startActivity(intent);
    }

    protected void openFinancialTransactions(){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    protected void openManageDocuments(){
        Intent intent=new Intent(this,ShowDocumentsListActivity.class);
        startActivity(intent);
    }

    protected void openKym(){
        Intent intent=new Intent(this,KnowYourMasterActivity.class);
        startActivity(intent);
    }

    protected void openTrackElectronicExpenses(){
        Intent intent=new Intent(this,TrackElectronicExpensesActivity.class);
        startActivity(intent);
    }

    protected void openMyInvestments(){
        Intent intent=new Intent(this,InvestmentsActivity.class);
        startActivity(intent);
    }

    protected void openReminders(){
        Intent intent=new Intent(this,ManageRemindersListActivity.class);
        startActivity(intent);
    }


    public void addManualExpensesDetails(View view){
        Intent intent=new Intent(view.getContext(),AddEditExpensesActivity.class);
        startActivity(intent);

    }
    private void populateFinancialTransactionsTabs(){
        viewPager = (ViewPager) findViewById(R.id.expenseViewPager);
        String expenseTitles[]=new String[]{"Manual", "Electronic","Summary" };
        TabFragment expenseTabFragment=new ExpenseTabFragment();
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),expenseTitles,expenseTabFragment);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.expenseTabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    public void configureScanSMS(View view){
        Intent intent= new Intent(view.getContext(),ConfigureScanSMSActivity.class);
        startActivity(intent);

    }

    public void displayTagsForExpenseSummary(View view){
        Intent intent= new Intent(view.getContext(),ConfigureExpenseSummaryActivity.class);
        startActivity(intent);

    }

    public void displayExpenseSummary(View view){
        init();
        int id=expenseSummaryRadioGroup1.getCheckedRadioButtonId();
        int id1=expenseSummaryRadioGroup2.getCheckedRadioButtonId();
        int [][] quarterArray=new int [][]{{0,2},{3,5},{6,8},{9,11}};
        if(id==R.id.expenseSummaryFilterOn) {
            Calendar now = Calendar.getInstance();
            Calendar start = Calendar.getInstance();
            Calendar end = Calendar.getInstance();
            switch (id1) {
                case R.id.expenseSummaryWeeklyOption:
                    int dayInWeek = now.get(Calendar.DAY_OF_WEEK);
                    int daysAfter = 7 - dayInWeek;
                    start.add(Calendar.DATE, -dayInWeek);
                    end.add(Calendar.DATE, daysAfter);
                    break;

                case R.id.expenseSummaryMonthlyOption:
                    Log.d("option", "monthly");
                    int month = now.get(Calendar.MONTH);
                    Log.d("month ", month + "");
                    start.set(now.get(Calendar.YEAR), month, 1);
                    if (month == 0 || month == 2 || month == 4 || month == 6 || month == 7 || month == 9 || month == 11)
                        end.set(now.get(Calendar.YEAR), month, 31);
                    if (month == 3 || month == 5 || month == 8 || month == 10)
                        end.set(now.get(Calendar.YEAR), month, 30);
                    if (month == 1)
                        end.set(now.get(Calendar.YEAR), month, 28);
                    break;


                case R.id.expenseSummaryQuarterlyOption:
                    Log.d("option", "quarterly");
                    int quarter = (now.get(Calendar.MONTH) / 3) + 1;
                    int[] months = quarterArray[quarter - 1];
                    start.set(now.get(Calendar.YEAR), months[0], 1);
                    if (quarter == 1 || quarter == 4)
                        end.set(now.get(Calendar.YEAR), months[1], 31);
                    else
                        end.set(now.get(Calendar.YEAR), months[1], 30);

                    break;

                case R.id.expenseSummaryAnnuallyOption:
                    Log.d("option", "annually");
                    int year = now.get(Calendar.YEAR);
                    start.set(now.get(Calendar.YEAR), Calendar.JANUARY, 1);
                    end.set(now.get(Calendar.YEAR), Calendar.DECEMBER, 31);
                    break;
            }

            Log.d("start date ", start.getTime().toString());
            Log.d("end date ", end.getTime().toString());
            ExpensesHelper expensesHelper = new ExpensesHelper();
            PersonalAssistantDatabase personalAssistantDatabase = DatabaseHelper.getDatabase(this);
            try {
                List<ExpenseVO> expenseVOS=expensesHelper.fetchExpenseVOsBetweenDates(personalAssistantDatabase, start.getTime(), end.getTime());
                Log.d("expenseVOS size between date ",expenseVOS.size()+"");

                populateSummaryExpensesList(this,expenseVOS);

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }



    }

    private void populateSummaryExpensesList(Context context,List<ExpenseVO> expenseVOs) {
        summaryExpensesListView = (ListView) findViewById(R.id.listSummaryExpenses);
        summaryExpensesAdapter= new SimpleAdapter(
                this,
                summaryExpensesList,
                R.layout.three_line_list_item_expenses,
                new String[] {expenseAllTags,expenseDate,expenseAmount},
                new int[] {R.id.text1,R.id.text2,R.id.text3}
        );
        summaryExpensesListView.setAdapter(summaryExpensesAdapter);
        summaryExpensesList.clear();
        double expense=0.0;
        Set<String> summaryTags=mSharedPrefSummaryTags.getStringSet(SELECTED_TAG_KEY, new HashSet<>());

        for (ExpenseVO expenseVO : expenseVOs) {

            HashMap map = new HashMap();
            List<String> expenseTags = new ArrayList<>();
            List<String> expensedFor=expenseVO.getExpensedForTags();
            List<String> expensedOn=expenseVO.getExpensedOnTags();
            expenseTags.addAll(expensedFor);
            expenseTags.addAll(expensedOn);
            boolean isTagPresent=false;
            for(String expenseTag:expenseTags){
                if(summaryTags.contains(expenseTag)) {
                    isTagPresent=true;
                    break;
                }
            }
            if(!isTagPresent && expenseSummarySelectToggleTagsObj.isChecked()) continue;
            map.put(expenseAllTags, expenseTags);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");
            map.put(expenseDate, sdf.format(expenseVO.getExpenseDate()));
            map.put(expenseAmount, "₹ " + expenseVO.getExpenseAmount());
            expense=expense+expenseVO.getExpenseAmount();
            summaryExpensesList.add(map);
        }

        DecimalFormat format=new DecimalFormat();
        format.applyPattern("###.##");
        expenseSummaryTotalObj.setText("Total : ₹ "+format.format(expense));


    }



    public void scanSMSForExpenses(View view){
        SharedPreferences expenseSmsFilterSharedPref=getSharedPreferences(EXPENSED_SMS_FILTER_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        Set<String> expenseSmsFilterSet=expenseSmsFilterSharedPref.getStringSet(EXPENSED_SMS_FILTER_SHARED_PREFERENCE_KEY,new HashSet<>());
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_SMS},SMS_READ_PERMISSION);
        Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);
        List<Map<String,String>> smsData=new ArrayList<>();
        if (cursor.moveToFirst()) { // must check the result to prevent exception
            int i=0;
            do {
                Map<String,String> smsRowMap=new HashMap<>();
                String msgData = "";
                for(int idx=0;idx<cursor.getColumnCount();idx++)
                {
                    smsRowMap.put(cursor.getColumnName(idx),cursor.getString(idx));
                }
                smsData.add(smsRowMap);
            } while (cursor.moveToNext());
        } else {
            // empty box, no SMS
        }
        ExpensesHelper expensesHelper=new ExpensesHelper();
        PersonalAssistantDatabase personalAssistantDatabase= DatabaseHelper.getDatabase(this);
        List<ExpenseVO> existingExpenseVOs=new ArrayList<>();
        try {
            existingExpenseVOs=expensesHelper.fetchAllExpenseVOs(personalAssistantDatabase);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        for(Map<String ,String> smsRow:smsData){
            Log.d("smsRow " ,smsRow.toString());
            String smsMessage=smsRow.get("body");
            String smsDateSent=smsRow.get("date_sent");
            List<Long> existingTimestamps=existingExpenseVOs.stream().map( x-> x.getExpenseTimeStamp()).collect(Collectors.toList());
            for(Long l:existingTimestamps){
                Log.d("existingTimestamps ",l+"");
            }
            for(String smsFilterKeyword:expenseSmsFilterSet) {
                String[] smsWords=smsMessage.split(" ");
                for(String smsWord:smsWords) {
                    if (smsWord.equals(smsFilterKeyword)) {
                        ExpenseVO expenseVO=Utils.extractExpenseFromMessage(smsMessage);
                        expenseVO.setManuallyEntered(false);
                        long timeStamp=Long.parseLong(smsDateSent);
                        expenseVO.setExpenseTimeStamp(timeStamp);
                        expenseVO.setExpenseDate(timeStamp);

                        expenseVO.setExpenseId(-1);
                        try{
                            if(!existingTimestamps.contains(expenseVO.getExpenseTimeStamp()))
                                expensesHelper.persistExpense(personalAssistantDatabase,expenseVO);
                            Log.d("expenseVO timestamp : ",expenseVO.getExpenseTimeStamp()+"");
                        }catch (SQLException ex){
                            ex.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }


    }
}
