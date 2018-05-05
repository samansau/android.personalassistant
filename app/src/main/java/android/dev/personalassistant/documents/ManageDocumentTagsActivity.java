package android.dev.personalassistant.documents;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.utils.Constants;
import android.dev.personalassistant.R;
import android.dev.personalassistant.tags.TaggingInput;
import android.dev.personalassistant.tags.TaggingUtility;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;

public class ManageDocumentTagsActivity extends AppCompatActivity implements Constants {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_documents_tags);
        final TextInputEditText editDocumentsTag=(TextInputEditText)findViewById(R.id.editDocumentsTags);
        final SharedPreferences mSharedPref = this.getSharedPreferences(DOCUMENTS_TAG_SHARED_PREFERENCE,Context.MODE_PRIVATE);
        TaggingInput taggingInput=new TaggingInput(this,mSharedPref,editDocumentsTag,DOCUMENTS_TAG_KEYS,DOCUMENTS_TAG_MAX_KEY);
        editDocumentsTag.setOnKeyListener(taggingInput);
        int mExpensesTagMaxKey= mSharedPref.getInt(DOCUMENTS_TAG_MAX_KEY, 0);
        if(mExpensesTagMaxKey==0){
            TaggingUtility.populateDocumentTagSharedPrefs(mSharedPref);
        }
        GridLayout mtableLayout=(GridLayout)findViewById(R.id.listDocumentsTags);
        TaggingUtility taggingUtility=new TaggingUtility(this,mtableLayout);
        taggingUtility.populateTagView(mSharedPref,DOCUMENTS_TAG_KEYS,editDocumentsTag);

    }


}
