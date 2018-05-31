package android.dev.personalassistant.investments;

import android.content.Intent;
import android.dev.personalassistant.utils.Utils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SIPInvestmentListActivity extends AppCompatActivity {
    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sipinvestment_list);

        final ListView listView = (ListView) findViewById(R.id.listSips);
        final ListAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.three_line_list_item,
                new String[] {"rank","model"},
                new int[] {R.id.text1,R.id.text2}
        );
        Utils.populateList(list);
        listView.setAdapter(adapter);

    }

    public void addUpdateSIP(View view){
        Intent intent=new Intent(this,SIPInvestmentDetailActivity.class);
        startActivity(intent);
    }
}
