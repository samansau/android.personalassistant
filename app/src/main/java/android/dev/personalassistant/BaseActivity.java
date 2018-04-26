package android.dev.personalassistant;

import android.content.Intent;
import android.dev.personalassistant.android.dev.personalassistant.adapters.TabAdapter;
import android.dev.personalassistant.android.dev.personalassistant.adapters.ExpenseTabFragment;
import android.dev.personalassistant.android.dev.personalassistant.adapters.TabFragment;
import android.dev.personalassistant.kym.KnowYourMasterActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by saurabh on 3/31/18.
 */


public class BaseActivity  extends AppCompatActivity {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;

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

    private void populateFinancialTransactionsTabs(){
        viewPager = (ViewPager) findViewById(R.id.expenseViewPager);
        String expenseTitles[]=new String[]{"All", "Electronic", "Manual"};
        TabFragment expenseTabFragment=new ExpenseTabFragment();
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),expenseTitles,expenseTabFragment);
        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.expenseTabs);
        tabLayout.setupWithViewPager(viewPager);

    }
}
