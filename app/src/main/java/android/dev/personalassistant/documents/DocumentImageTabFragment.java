package android.dev.personalassistant.documents;

import android.dev.personalassistant.R;
import android.dev.personalassistant.tabs.TabFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.dev.personalassistant.utils.Constants.DOCUMENTS_IMAGES;

/**
 * Created by saurabh on 4/5/18.
 */

public class DocumentImageTabFragment extends TabFragment {

    private ImageView imageView ;
    private TextView textView;
    int pos=0;
    public  Fragment getInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        bundle.putStringArrayList(DOCUMENTS_IMAGES,this.getArguments().getStringArrayList(DOCUMENTS_IMAGES));
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
        textView = (TextView)view.findViewById(R.id.documentImageTextIndex);
        Bundle bundle=this.getArguments();
        Log.i("xx",position+"");
        ArrayList<String> documentImages=bundle.getStringArrayList(DOCUMENTS_IMAGES);
        textView.setText((position+1)+" of "+documentImages.size());


        String uriStr=documentImages.get(documentImages.size()-(position+1));

        Uri uri=Uri.parse(uriStr);
        imageView.setImageURI(uri);
        imageView.setMaxHeight(10);
        imageView.setMaxWidth(10);

    }

}
