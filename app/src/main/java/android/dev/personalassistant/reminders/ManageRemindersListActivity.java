package android.dev.personalassistant.reminders;

import android.app.ListActivity;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ManageRemindersListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_reminders_list);
    }


    public void addManageReminders(View view){
        Log.d("Called","addManageReminders");
        Intent intent=new Intent(this,ManageRemindersDetailsActivity.class);
        startActivity(intent);

    }
}
