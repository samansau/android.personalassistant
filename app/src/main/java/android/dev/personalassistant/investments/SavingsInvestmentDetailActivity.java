package android.dev.personalassistant.investments;

import android.dev.personalassistant.utils.Utils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SavingsInvestmentDetailActivity extends AppCompatActivity {
    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings_investment_detail);


        final ListView listView = (ListView) findViewById(R.id.listSavingsDetails);
        final ListAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.two_line_list_item,
                new String[] {"rank","model"},
                new int[] {R.id.text1,R.id.text2}
        );
        Utils.populateList(list);
        listView.setAdapter(adapter);
    }
}
