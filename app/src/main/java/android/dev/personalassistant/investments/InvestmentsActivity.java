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

public class InvestmentsActivity extends AppCompatActivity {
    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investments);
        final ListView listView = (ListView) findViewById(R.id.listInvestments);
        final ListAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.two_line_list_item,
                new String[] {"rank","model"},
                new int[] {R.id.text1,R.id.text2}
        );
        Utils.populateList(list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                switch (pos){
                    case 0:// Savings
                        Intent intentSavings =new Intent(v.getContext(),SavingsInvestmentListActivity.class);
                        startActivity(intentSavings);
                        break;
                    case 1: // PPF
                        Intent intentPPF =new Intent(v.getContext(),PPFInvestmentListActivity.class);
                        startActivity(intentPPF);
                        break;
                    case 2: // FD
                        Intent intentFD =new Intent(v.getContext(),FixedDepositsInvestmentListActivity.class);
                        startActivity(intentFD);
                        break;
                    case 3: // SIP
                        break;
                    case 4: // RD
                        break;
                    case 5: // Mutual Funds
                        break;
                }
            }
        });
    }



}
