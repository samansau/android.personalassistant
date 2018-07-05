package android.dev.personalassistant.expenses;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.dev.personalassistant.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Map;

import static android.dev.personalassistant.utils.Constants.SMS_READ_PERMISSION;

public class ExpensesShowElectronicActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_show_electronic);
    }




}
