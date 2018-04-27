package android.dev.personalassistant.android.dev.personalassistant.adapters;

import android.content.Intent;
import android.content.SharedPreferences;
import android.dev.personalassistant.AddEditExpensesActivity;
import android.dev.personalassistant.R;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.dev.personalassistant.Constants.DOCUMENTS_IMAGES;

/**
 * Created by saurabh on 4/5/18.
 */

public class DocumentImageTabFragment extends TabFragment{

    private ImageView imageView;

    public  Fragment getInstance(int position) {
        Bundle bundle = this.getArguments();
        bundle.putInt("pos", position);
       //DocumentImageTabFragment tabFragment = new DocumentImageTabFragment();

        this.setArguments(bundle);
        return this;
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
        Bundle bundle=this.getArguments();
        ArrayList<String> documentImages=bundle.getStringArrayList(DOCUMENTS_IMAGES);
        String uriStr=documentImages.get(position);
        Uri uri=Uri.parse(uriStr);
        imageView.setImageURI(uri);
    }

}
