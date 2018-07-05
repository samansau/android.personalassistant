package android.dev.personalassistant.tags;

import android.content.Context;
import android.content.SharedPreferences;
import android.dev.personalassistant.main.BaseActivity;
import android.dev.personalassistant.R;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import static android.dev.personalassistant.utils.Constants.SELECTED_TAG_KEY;
import static android.dev.personalassistant.utils.Constants.SELECTED_TAG_KEYS;

/**
 * Created by saurabh on 4/10/18.
 */

public class TaggingView implements View.OnClickListener,View.OnLongClickListener {

    SharedPreferences mSharedPref;
    Set<String> selectedTags;

    public TaggingView(SharedPreferences mSharedPref){
        if(mSharedPref!=null) {
            this.mSharedPref = mSharedPref;
            selectedTags = mSharedPref.getStringSet(SELECTED_TAG_KEY, new HashSet<>());
        }
    }




    public void onClick(View view) {
        if(mSharedPref!=null) {
            mSharedPref.edit().clear().commit();
            TextView tagClicked = (TextView) view;
            String tag = tagClicked.getText().toString();
            if (selectedTags.contains(tag)) {
                selectedTags.remove(tag);
                tagClicked.setBackgroundResource(R.drawable.rounded_border_layout);
            } else {
                selectedTags.add(tag);
                tagClicked.setBackgroundResource(R.drawable.rounded_border_layout_selected);
            }
            mSharedPref.edit().putStringSet(SELECTED_TAG_KEY, selectedTags).commit();
        }

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }

//    public boolean onLongClick(View view) {
//        int mSelectedTagKey=mSharedPref.getInt(SELECTED_TAG_KEY, -1);
//        if(mSelectedTagKey!=-1) {
//            SharedPreferences.Editor editor = mSharedPref.edit();
//            editor.remove(mSelectedTagKey + "").commit();
//            Set<String> tagKeys = mSharedPref.getStringSet(tagKey, null);
//            try {
//                tagKeys.remove(mSelectedTagKey + "");
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            editor.putStringSet(tagKey, tagKeys);
//            BaseActivity myActivity = (BaseActivity)view.getContext();
//            myActivity.recreate();
//            editor.putInt(SELECTED_TAG_KEY,-1);
//            editor.commit();
//            return true;
//        }else{
//            return false;
//        }
//
//    }

}
