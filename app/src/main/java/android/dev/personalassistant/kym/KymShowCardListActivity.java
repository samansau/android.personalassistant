package android.dev.personalassistant.kym;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.dev.personalassistant.R;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class KymShowCardListActivity extends AppCompatActivity {
    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_card_list);
        ListView listView = (ListView) findViewById(R.id.listCards);

        ListAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.three_line_list_item,
                new String[] {"rank","model"},
                new int[] {R.id.text1,R.id.text2}
        );

        populateList();
        listView.setAdapter(adapter);
    }

    public void addCardDetails(View view){
        Intent intent=new Intent(this,KymShowCardDetailsActivity.class);
        startActivity(intent);
    }

    private void populateList() {
        list.clear();
        HashMap map = new HashMap();
        map.put("rank", "1");
        map.put("model", "SBI Card");
        list.add(map);

        map = new HashMap();
        map.put("rank", "2");
        map.put("model", "HDFC Card");
        list.add(map);

        map = new HashMap();
        map.put("rank", "3");
        map.put("model", "Axis Card");
        list.add(map);
        map = new HashMap();
        map.put("rank", "4");
        map.put("model", "ICICI Card");
        list.add(map);

    }
}
