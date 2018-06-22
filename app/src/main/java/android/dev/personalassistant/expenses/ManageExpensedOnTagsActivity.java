package android.dev.personalassistant.expenses;

import android.content.Intent;
import android.dev.personalassistant.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ManageExpensedOnTagsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_expensed_on_tags);
    }

    public void expensedOnGroupTagsEdit(View view){
        Intent intent = new Intent(view.getContext(),ManageExpensedOnTagsDetailsActivity.class);
        startActivity(intent);

    }
    public void expensedOnElementalTagsEdit(View view){

    }
}
