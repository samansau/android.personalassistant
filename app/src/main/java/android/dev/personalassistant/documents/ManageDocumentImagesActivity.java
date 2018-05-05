package android.dev.personalassistant.documents;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.dev.personalassistant.tabs.TabAdapter;
import android.dev.personalassistant.tabs.TabFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static android.dev.personalassistant.utils.Constants.DOCUMENTS_IMAGES;
import static android.dev.personalassistant.utils.Constants.DOCUMENTS_TAG_SHARED_PREFERENCE;

public class ManageDocumentImagesActivity extends AppCompatActivity {
    SharedPreferences mSharedPref = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSharedPref=this.getSharedPreferences(DOCUMENTS_TAG_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_document_images);
        populateDocumentImageTabs();
    }

    private void populateDocumentImageTabs(){
        ViewPager viewPager;viewPager = (ViewPager) findViewById(R.id.documentImageViewPager);
        Set<String> documentImages=mSharedPref.getStringSet(DOCUMENTS_IMAGES,new HashSet<String>());
        TabFragment documentImageTabFragment=new DocumentImageTabFragment();
        Bundle bundle=new Bundle();
        bundle.putStringArrayList(DOCUMENTS_IMAGES,new ArrayList<String>(documentImages));
        documentImageTabFragment.setArguments(bundle);
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),documentImages.size(),documentImageTabFragment);
        viewPager.setAdapter(adapter);

        //TabLayout tabLayout = (TabLayout) findViewById(R.id.expenseTabs);
       // tabLayout.setupWithViewPager(viewPager);

    }
}
