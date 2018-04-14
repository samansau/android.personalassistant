package android.dev.personalassistant.utils;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.view.KeyEvent;
import android.view.View;

import static android.dev.personalassistant.Constants.SELECTED_TAG_KEY;

/**
 * Created by saurabh on 4/14/18.
 */

public class TaggingInput implements View.OnKeyListener {
    SharedPreferences mSharedPref;
    TextInputEditText inputTag;
    Activity parentActivity;
    String tagKey;
    String tagMaxKey;
    public TaggingInput(Activity parentActivity,
                        SharedPreferences mSharedPref,
                        TextInputEditText inputTag,String tagKey,String tagMaxKey){
        this.parentActivity=parentActivity;
        this.mSharedPref=mSharedPref;
        this.inputTag=inputTag;
        this.tagKey=tagKey;
        this.tagMaxKey=tagMaxKey;
    }

    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        int mSelectedTagKey= mSharedPref.getInt(SELECTED_TAG_KEY, -1);
        if(keyEvent.getAction()==KeyEvent.ACTION_DOWN) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.KEYCODE_ENTER:
                    String tag=inputTag.getText().toString().trim();
                    if(tag.length()>0) {
                        //persistTagChanges(tag);
                        mSelectedTagKey=mSharedPref.getInt(SELECTED_TAG_KEY, -1);
                        TaggingUtility.persistTagChanges(tag,mSharedPref,mSelectedTagKey,tagKey,tagMaxKey);
                        parentActivity.recreate();

                        SharedPreferences.Editor editor=mSharedPref.edit();
                        editor.putInt(SELECTED_TAG_KEY,-1);
                        editor.commit();
                    }
                    return true;
            }
        }
        return false;
    }
}
