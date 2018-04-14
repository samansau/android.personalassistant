package android.dev.personalassistant.utils;

import android.content.SharedPreferences;
import android.dev.personalassistant.BaseActivity;
import android.dev.personalassistant.R;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Set;

import static android.dev.personalassistant.Constants.SELECTED_TAG_KEY;
import static java.security.AccessController.getContext;

/**
 * Created by saurabh on 4/10/18.
 */

public class TaggingView implements View.OnClickListener,View.OnLongClickListener {

    TableLayout mtableLayout;
    SharedPreferences mSharedPref;
    TextInputEditText editTag;
    String tagKey;

    public TaggingView(TableLayout mtableLayout,SharedPreferences mSharedPref,TextInputEditText editTag,String tagKey){
        this.mtableLayout=mtableLayout;
        this.mSharedPref=mSharedPref;
        this.editTag=editTag;
        this.tagKey=tagKey;
    }



    public void onClick(View view) {
        int mSelectedTagKey= mSharedPref.getInt(SELECTED_TAG_KEY, -1);

        for(int i=0;i<mtableLayout.getChildCount();i++){
            mtableLayout.getChildAt(i).setBackgroundColor(mtableLayout.getSolidColor());
        }

        TableRow tagClicked=(TableRow)view;
        tagClicked.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_bright));

        if(mSelectedTagKey==tagClicked.getId()){
            tagClicked.setBackgroundColor(mtableLayout.getSolidColor());
            mSelectedTagKey=-1;
        }else{
            mSelectedTagKey=tagClicked.getId();
            editTag.setText(((TextView)tagClicked.getChildAt(0)).getText());
        }
        SharedPreferences.Editor editor=mSharedPref.edit();
        editor.putInt(SELECTED_TAG_KEY,mSelectedTagKey);
        editor.commit();

    }

    public boolean onLongClick(View view) {
        int mSelectedTagKey=mSharedPref.getInt(SELECTED_TAG_KEY, -1);
        if(mSelectedTagKey!=-1) {
            SharedPreferences.Editor editor = mSharedPref.edit();
            editor.remove(mSelectedTagKey + "").commit();
            Set<String> tagKeys = mSharedPref.getStringSet(tagKey, null);
            try {
                tagKeys.remove(mSelectedTagKey + "");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            editor.putStringSet(tagKey, tagKeys);
            BaseActivity myActivity = (BaseActivity)view.getContext();
            myActivity.recreate();
            editor.putInt(SELECTED_TAG_KEY,-1);
            editor.commit();
            return true;
        }else{
            return false;
        }

    }

}
