package android.dev.personalassistant.expenses;

import android.content.Context;
import android.content.Intent;
import android.dev.personalassistant.R;
import android.dev.personalassistant.tabs.TabFragment;
import android.dev.personalassistant.utils.Utils;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

import static android.dev.personalassistant.utils.Keys.expenseAmount;
import static android.dev.personalassistant.utils.Keys.expenseDate;
import static android.dev.personalassistant.utils.Keys.expenseDescription;
import static android.dev.personalassistant.utils.Keys.expenseTags;

/**
 * Created by saurabh on 4/5/18.
 */

public class ExpenseTabFragment extends TabFragment {

    private TextView textView;
    static final ArrayList<Map<String,String>> allExpensesList =
            new ArrayList<Map<String,String>>();
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

        View view =null;
        switch(position) {
            case 0:
                view = getAllExpensesView(inflater, container);
                break;
        }
        return view;

        //return inflater.inflate(R.layout.expense_tabs, container, false);
    }


    private View getAllExpensesView(LayoutInflater inflater, ViewGroup container){
        View view;
        view= inflater.inflate(R.layout.activity_expenses_show_all_list, container, false);
        ListView allExpensesListView = (ListView) view.findViewById(R.id.listAllExpenses);
        ListAdapter allExpensesAdapter = new SimpleAdapter(
                view.getContext(),
                allExpensesList,
                R.layout.four_line_list_item,
                new String[] {expenseTags,expenseDate,expenseAmount,expenseDescription},
                new int[] {R.id.text1,R.id.text2,R.id.text3,R.id.text4}
        );
        populateAllExpensesList(getContext());
        allExpensesListView.setAdapter(allExpensesAdapter);
        return view;
    }

    private void populateAllExpensesList(Context context){
        Utils.populateListOf4Items(allExpensesList);
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        textView = (TextView) view.findViewById(R.id.textView);
//
//
//        if(position==2) {
//            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
//            fab.setVisibility(View.INVISIBLE);
//            fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent=new Intent(getActivity(),AddEditExpensesActivity.class);
//                    startActivity(intent);
//                }
//            });
//        }
//
//        if(position==1){
//            textView.setText("It seems you have not configured to read your SMS to " +
//                    "automatically keep track of " +
//                    "expenses/transactions done through electronic mode.Please go to Track Electronic Expenses" +
//                    " in main menu and configure the same");
//        }
//    }

}
