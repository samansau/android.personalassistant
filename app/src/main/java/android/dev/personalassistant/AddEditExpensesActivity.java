package android.dev.personalassistant;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.utils.TaggingUtility;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AddEditExpensesActivity extends BaseActivity implements Constants {
    String [] mExpenseTags=new String[]{};
    SharedPreferences mSharedPref= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        savedInstanceState=new Bundle();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_edit_expenses);
        mSharedPref= this.getSharedPreferences(EXPENSE_TAG_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        populateExpenseTagsArray();
        //mExpenseTags=new String[] {"India","China","Afganistan","Pakistan","Srilanka"};
        //populateAutoCompleteExpenseTags();
        mDrawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        mActionBarDrawerToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        initDrawer();
        createExpenseToolBar();



        final GridLayout mGridLayout=(GridLayout)findViewById(R.id.selectedExpensesTags);
        final TaggingUtility taggingUtilitySelected=new TaggingUtility(this,mGridLayout);
        taggingUtilitySelected.populateTagView(mSharedPref, SELECTED_TAG_KEYS, null);
        mSharedPref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(SELECTED_TAG_KEY.equals(key)) {
                    mGridLayout.removeAllViews();
                    taggingUtilitySelected.populateTagView(mSharedPref, SELECTED_TAG_KEYS, null);
                }
            }
        });


    }

    private void populateExpenseTagsArray(){

        Set<String> tagKeys=mSharedPref.getStringSet(EXPENSE_TAG_KEYS,null);
        List<String> tagList=new ArrayList<String>();
        tagList.add("Select Expense Tag");
        for(String key:tagKeys){
            String tagName=mSharedPref.getString(key,null);
            tagList.add(tagName);
        }
        mExpenseTags=tagList.toArray(mExpenseTags);




    }

//    private void populateAutoCompleteExpenseTags(){
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,mExpenseTags);
//
//
//        MultiAutoCompleteTextView multiAutoCompleteExpenseTag = (MultiAutoCompleteTextView)findViewById(R.id.expenseTagsList);
//        multiAutoCompleteExpenseTag.setAdapter(adapter);
//        multiAutoCompleteExpenseTag.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
//    }
}
