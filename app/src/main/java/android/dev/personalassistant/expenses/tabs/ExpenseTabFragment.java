package android.dev.personalassistant.expenses.tabs;

import android.content.Intent;
import android.dev.personalassistant.expenses.components.AddEditExpensesActivity;
import android.dev.personalassistant.R;
import android.dev.personalassistant.tabs.TabFragment;
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

public class ExpenseTabFragment extends TabFragment {

    private TextView textView;

    public  Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        ExpenseTabFragment tabFragment = new ExpenseTabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.expense_tabs, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) view.findViewById(R.id.textView);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        if(position!=2) {
            fab.setVisibility(View.INVISIBLE);
        }

        if(position==1){
            textView.setText("It seems you have not configured to read your SMS to " +
                    "automatically keep track of " +
                    "expenses/transactions done through electronic mode.Please go to Track Electronic Expenses" +
                    " in main menu and configure the same");
        }


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),AddEditExpensesActivity.class);
                startActivity(intent);
            }
        });

    }

}
