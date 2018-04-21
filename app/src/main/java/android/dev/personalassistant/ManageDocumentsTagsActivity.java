package android.dev.personalassistant;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.SharedPreferences;
import android.dev.personalassistant.utils.TaggingInput;
import android.dev.personalassistant.utils.TaggingUtility;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

public class ManageDocumentsTagsActivity extends AppCompatActivity implements Constants{
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

        final Button populateTagsDoneButton=(Button)findViewById(R.id.populateTagsDone);

        //setContentView(R.layout.activity_manage_documents_tags);
        //GridLayout mGridLayout=(GridLayout)findViewById(R.id.selectedDocumentsTags);
        //final TaggingUtility taggingUtilitySelected=new TaggingUtility(this,mGridLayout);
//        populateTagsDoneButton.setOnClickListener(new View.OnClickListener() {
//                                                      @Override
//                                                      public void onClick(View view) {
//                                                          ComponentName parentActivity=getCallingActivity();
//                                                          parentActivity.
//                                                          Activity parentActivity=(Activity)((ContextWrapper)view.getParent()).getBaseContext();
//                                                          GridLayout mGridLayout=parentActivity.findViewById(R.id.selectedDocumentsTags);
//                                                          TaggingUtility taggingUtilitySelected=new TaggingUtility(parentActivity,mGridLayout);
//                                                          taggingUtilitySelected.populateTagView(mSharedPref,SELECTED_TAG_KEYS,null);
//
//                                                      }
//                                                  }
//        );
    }


}
