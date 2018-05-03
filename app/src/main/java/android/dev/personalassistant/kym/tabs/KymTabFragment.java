package android.dev.personalassistant.kym.tabs;

import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.kym.components.KymShowBankListActivity;
import android.dev.personalassistant.tabs.TabFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

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
                view= inflater.inflate(R.layout.activity_kym_show_bank_details, container, false);
                break;
        }
        return view;
    }





    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    }

}
