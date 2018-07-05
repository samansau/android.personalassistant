package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.dev.personalassistant.main.BaseActivity;
import android.dev.personalassistant.utils.Constants;
import android.dev.personalassistant.R;
import android.dev.personalassistant.tags.TaggingInput;
import android.dev.personalassistant.tags.TaggingUtility;
import android.support.design.widget.TextInputEditText;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

public class ManageExpenseTagsActivity extends BaseActivity implements Constants {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expense_tags);

    }



}
