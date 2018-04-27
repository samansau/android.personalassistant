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
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by saurabh on 4/5/18.
 */

public class DocumentImageTabFragment extends TabFragment{

    private ImageView imageView;

    public  Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        DocumentImageTabFragment tabFragment = new DocumentImageTabFragment();
        tabFragment.setArguments(bundle);
        return tabFragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.document_images_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (ImageView) view.findViewById(R.id.documentImageFragment);




    }

}
