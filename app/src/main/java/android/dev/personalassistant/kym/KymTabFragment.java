package android.dev.personalassistant.kym;

import android.dev.personalassistant.R;
import android.dev.personalassistant.tabs.TabFragment;
import android.dev.personalassistant.utils.Utils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saurabh on 4/5/18.
 */

public class KymTabFragment extends TabFragment {
    int position;
    private TextView textView;
    static final ArrayList<HashMap<String,String>> list =
            new ArrayList<HashMap<String,String>>();

    public  Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        KymTabFragment tabFragment = new KymTabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =null;
        switch(position){
            case 0:
                view= inflater.inflate(R.layout.kym_personal_fragment, container, false);
                break;
            case 1:
                view= inflater.inflate(R.layout.activity_kym_show_bank_list, container, false);
                ListView listView = (ListView) view.findViewById(R.id.listBanks);
                ListAdapter adapter = new SimpleAdapter(
                        view.getContext(),
                        list,
                        R.layout.two_line_list_item,
                        new String[] {"rank","model"},
                        new int[] {R.id.text1,R.id.text2}
                );
                Utils.populateList(list);
                listView.setAdapter(adapter);
                break;
        }
        return view;
    }





    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}
