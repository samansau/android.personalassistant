package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.dev.personalassistant.tags.TaggingUtility;
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

import static android.dev.personalassistant.utils.Constants.EXPENSED_SMS_FILTER_SHARED_PREFERENCE;
import static android.dev.personalassistant.utils.Constants.EXPENSED_SMS_FILTER_SHARED_PREFERENCE_KEY;

public class ConfigureExpenseSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure_scan_sms);
    }




}
