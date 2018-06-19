package android.dev.personalassistant.expenses;

import android.content.Intent;
import android.dev.personalassistant.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ExpensesShowManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_show_manual);
    }



    public void addManualExpensesDetails(View view){
        Intent intent=new Intent(view.getContext(),AddEditExpensesActivity.class);
        startActivity(intent);

    }
}
