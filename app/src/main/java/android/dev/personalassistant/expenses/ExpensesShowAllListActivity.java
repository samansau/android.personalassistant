package android.dev.personalassistant.expenses;

import android.app.ListActivity;
import android.dev.personalassistant.R;
import android.os.Bundle;

public class ExpensesShowAllListActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_show_summary);
    }

}
