package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.tags.TaggingUtility;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.dev.personalassistant.R;
import android.widget.EditText;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.dev.personalassistant.utils.Constants.EXPENSED_FOR_TAG_SHARED_PREFERENCE;
import static android.dev.personalassistant.utils.Constants.EXPENSED_SMS_FILTER_SHARED_PREFERENCE;
import static android.dev.personalassistant.utils.Constants.EXPENSED_SMS_FILTER_SHARED_PREFERENCE_KEY;

public class ConfigureScanSMSActivity extends AppCompatActivity {
    EditText expenseSmsFilterCriteriaFieldObj;
    private GridLayout expenseSmsFilterCriteriaGridLayoutObj;
    SharedPreferences expenseSmsFilterSharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_scan_sms);
        expenseSmsFilterCriteriaFieldObj=findViewById(R.id.expenseSmsFilterCriteriaField);
        expenseSmsFilterCriteriaGridLayoutObj =findViewById(R.id.expenseSmsFilterCriteriaGridLayout);
        expenseSmsFilterSharedPref=getSharedPreferences(EXPENSED_SMS_FILTER_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        expenseSmsFilterCriteriaFieldObj.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if(keyEvent.getAction()==KeyEvent.ACTION_DOWN) {
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.KEYCODE_ENTER:
                            String tag= expenseSmsFilterCriteriaFieldObj.getText().toString().trim();
                            if(tag.length()>0) {
                                Set<String> filterSet=expenseSmsFilterSharedPref.getStringSet(EXPENSED_SMS_FILTER_SHARED_PREFERENCE_KEY,new HashSet<>());
                                filterSet.add(tag);
                                expenseSmsFilterSharedPref.edit().putStringSet(EXPENSED_SMS_FILTER_SHARED_PREFERENCE_KEY,filterSet).commit();
                                populateSMSFilterConfig();
                            }
                    }
                }
                return false;
            }
        });

        populateSMSFilterConfig();
    }


    public void populateSMSFilterConfig(){
        List<String> tags=new ArrayList<>(expenseSmsFilterSharedPref.getStringSet(EXPENSED_SMS_FILTER_SHARED_PREFERENCE_KEY,new HashSet<>()));
        TaggingUtility taggingUtility=new TaggingUtility(this, expenseSmsFilterCriteriaGridLayoutObj);
        taggingUtility.arrangeTagsOnGridLayout(this, expenseSmsFilterCriteriaGridLayoutObj,tags,null);

    }


}
