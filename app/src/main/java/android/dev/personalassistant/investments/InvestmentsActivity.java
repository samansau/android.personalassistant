package android.dev.personalassistant.investments;

import android.content.Intent;
import android.dev.personalassistant.enums.InvestmentCategory;
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

import static android.dev.personalassistant.utils.Keys.investmentCategory;
import static android.dev.personalassistant.utils.Keys.investmentCategoryPercent;
import static android.dev.personalassistant.utils.Keys.investmentCategoryTotal;

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
                R.layout.three_line_list_item_investment_summary,
                new String[] {investmentCategory,investmentCategoryPercent,investmentCategoryTotal},
                new int[] {R.id.text1,R.id.text2,R.id.text3}
        );
        populateInvestmentSummaryList();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> list, View v, int pos, long id) {
                switch (pos){
                    case 0:// Savings
                        Intent intentSavings =new Intent(v.getContext(),SavingsInvestmentListActivity.class);
                        startActivity(intentSavings);
                        break;
//                    case 1: // FD
//                        Intent intentFD =new Intent(v.getContext(),FixedDepositsInvestmentListActivity.class);
//                        startActivity(intentFD);
//                        break;
//                    case 2: // PPF
//                        Intent intentPPF =new Intent(v.getContext(),PPFInvestmentListActivity.class);
//                        startActivity(intentPPF);
//                        break;
//                    case 3: // SIP
//                        Intent intentSIP =new Intent(v.getContext(),SIPInvestmentListActivity.class);
//                        startActivity(intentSIP);
//                        break;
//                    case 4: // RD
//                        Intent intentRD =new Intent(v.getContext(),RDInvestmentListActivity.class);
//                        startActivity(intentRD);
//                        break;
//                    case 5: // Mutual Funds
//                        break;
                }
            }
        });
    }

    private void populateInvestmentSummaryList(){
        list.clear();

        for (InvestmentCategory investmentCategoryValues : InvestmentCategory.values()) {
            HashMap map = new HashMap();
            map.put(investmentCategory, investmentCategoryValues.getDisplayValue());
            map.put(investmentCategoryPercent,"0%");
            map.put(investmentCategoryTotal, "₹ 0");
            list.add(map);
        }
        HashMap map = new HashMap();
        map.put(investmentCategory, "Total");
        map.put(investmentCategoryPercent,"100%");
        map.put(investmentCategoryTotal, "₹ 0");
        list.add(map);

    }





}
