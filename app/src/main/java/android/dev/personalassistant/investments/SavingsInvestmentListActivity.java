package android.dev.personalassistant.investments;

import android.content.Intent;
import android.dev.personalassistant.utils.Utils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SavingsInvestmentListActivity extends AppCompatActivity {
    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings_investment_list);

        final ListView listView = (ListView) findViewById(R.id.listSavings);
        final ListAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.four_line_list_item,
                new String[] {"bank","branch","account_number","balance"},
                new int[] {R.id.text1,R.id.text2,R.id.text3,R.id.text4}
        );
        Utils.populateListOf4Items(list);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View view, int pos, long id) {
                showSavings(view);
            }
        });
    }

    public void showSavings(View view){
        Intent intent =new Intent(view.getContext(),SavingsInvestmentDetailActivity.class);
        startActivity(intent);
    }
}
