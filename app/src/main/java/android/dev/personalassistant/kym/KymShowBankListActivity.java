package android.dev.personalassistant.kym;

import android.app.ListActivity;
import android.dev.personalassistant.R;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Map;

public class KymShowBankListActivity extends ListActivity {


    static final ArrayList<Map<String,String>> list =
            new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kym_show_bank_list);

    }

}
