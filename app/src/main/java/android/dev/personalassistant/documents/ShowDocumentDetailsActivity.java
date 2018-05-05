package android.dev.personalassistant.documents;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.dev.personalassistant.R;
import android.dev.personalassistant.tags.TaggingUtility;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.ScrollView;

import java.util.HashSet;
import java.util.Set;

import static android.dev.personalassistant.utils.Constants.DOCUMENTS_IMAGES;
import static android.dev.personalassistant.utils.Constants.DOCUMENTS_TAG_SHARED_PREFERENCE;
import static android.dev.personalassistant.utils.Constants.SELECTED_TAG_KEY;
import static android.dev.personalassistant.utils.Constants.SELECTED_TAG_KEYS;

public class ShowDocumentDetailsActivity extends AppCompatActivity {
    private static final int READ_REQUEST_CODE = 42;
    SharedPreferences mSharedPref = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSharedPref=this.getSharedPreferences(DOCUMENTS_TAG_SHARED_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=mSharedPref.edit();
        editor.putStringSet(DOCUMENTS_IMAGES,new HashSet<String>());
        editor.commit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_document_details);
        createDocumentsToolBar();


        final GridLayout mGridLayout=(GridLayout)findViewById(R.id.selectedDocumentsTags);
        final ScrollView mScrollView=(ScrollView)findViewById(R.id.selectedDocumentsTagsScrollView);
        final TaggingUtility taggingUtilitySelected=new TaggingUtility(this,mGridLayout);
        taggingUtilitySelected.populateTagView(mSharedPref, SELECTED_TAG_KEYS, null);
        mSharedPref.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if(SELECTED_TAG_KEY.equals(key)) {
                    mGridLayout.removeAllViews();
                    taggingUtilitySelected.populateTagView(mSharedPref, SELECTED_TAG_KEYS, null);
                }
            }
        });

        mScrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openManageDocumentsTags();
            }
        });
    }

    protected void createDocumentsToolBar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolBar);
        toolbar.setTitle(R.string.manage_documents);
        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.document_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.documentTag:
                openManageDocumentsTags();
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    protected void openManageDocumentsTags(){
        Intent intent=new Intent(this,ManageDocumentTagsActivity.class);
        startActivity(intent);
    }

    protected void openManageDocumentsImages(View view){
        Intent intent=new Intent(this,ManageDocumentImagesActivity.class);
        startActivity(intent);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri uri = null;
            if (resultData != null) {
                uri = resultData.getData();

                GridLayout selectedDocumentsGridLayout =(GridLayout)findViewById(R.id.selectedDocumentsImages);

                ImageView imageView=new ImageView(selectedDocumentsGridLayout.getContext());
                imageView.setMaxWidth(50);
                imageView.setMaxHeight(50);

                imageView.setImageURI(uri);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openManageDocumentsImages(view);
                    }
                });
                Set<String> documentImages=mSharedPref.getStringSet(DOCUMENTS_IMAGES,new HashSet<String>());
                documentImages.add(uri.toString());
                SharedPreferences.Editor editor=mSharedPref.edit();
                editor.putStringSet(DOCUMENTS_IMAGES,documentImages);
                editor.commit();
                selectedDocumentsGridLayout.addView(imageView);
                //Log.i(TAG, "Uri: " + uri.toString());
                //showImage(uri);

            }

        }
    }

    public void openDocumentSelector(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }


}
