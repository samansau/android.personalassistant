package android.dev.personalassistant;

import android.dev.personalassistant.android.dev.personalassistant.adapters.DocumentImageTabFragment;
import android.dev.personalassistant.android.dev.personalassistant.adapters.KymTabFragment;
import android.dev.personalassistant.android.dev.personalassistant.adapters.TabAdapter;
import android.dev.personalassistant.android.dev.personalassistant.adapters.TabFragment;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ManageDocumentImagesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_document_images);
        populateDocumentImageTabs();
    }

    private void populateDocumentImageTabs(){
        ViewPager viewPager;viewPager = (ViewPager) findViewById(R.id.documentImageViewPager);
        String imgTitles[]=new String[]{"1", "2"};
        TabFragment documentImageTabFragment=new DocumentImageTabFragment();
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(),5,documentImageTabFragment);
        viewPager.setAdapter(adapter);

        //TabLayout tabLayout = (TabLayout) findViewById(R.id.expenseTabs);
       // tabLayout.setupWithViewPager(viewPager);

    }
}
