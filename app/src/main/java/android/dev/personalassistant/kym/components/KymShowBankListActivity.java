package android.dev.personalassistant.kym.components;

import android.app.ListActivity;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class KymShowBankListActivity extends ListActivity {


    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_bank_list);
        ListAdapter adapter = new SimpleAdapter(
                this,
                list,
                R.layout.bank_two_line_list_item,
                new String[] {"rank","model"},
                new int[] {R.id.text1,R.id.text2}
        );
        populateList();
        setListAdapter(adapter);



    }






    private void populateList() {
        HashMap map = new HashMap();
        map.put("rank", "1");
        map.put("model", "Samsung Galaxy Nexus");
        list.add(map);

        map = new HashMap();
        map.put("rank", "2");
        map.put("model", "Samsung Epic Touch 4G");
        list.add(map);

        map = new HashMap();
        map.put("rank", "3");
        map.put("model", "Samsung Epic Touch 5G");
        list.add(map);
        map = new HashMap();
        map.put("rank", "4");
        map.put("model", "Samsung Epic Touch 6G");
        list.add(map);

    }
}
