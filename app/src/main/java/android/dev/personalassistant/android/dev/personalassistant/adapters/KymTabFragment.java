package android.dev.personalassistant.android.dev.personalassistant.adapters;

import android.content.Intent;
import android.dev.personalassistant.AddEditExpensesActivity;
import android.dev.personalassistant.R;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by saurabh on 4/5/18.
 */

public class KymTabFragment extends TabFragment{
    int position;
    private TextView textView;

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
                view= inflater.inflate(R.layout.kym_financial_fragment, container, false);
                break;
        }
        return view;


    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {



    }

}
