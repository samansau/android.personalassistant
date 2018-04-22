package android.dev.personalassistant.utils;

import android.content.SharedPreferences;
import android.dev.personalassistant.BaseActivity;
import android.dev.personalassistant.R;
import android.support.design.widget.TextInputEditText;
import android.view.View;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

import static android.dev.personalassistant.Constants.SELECTED_TAG_KEY;
import static android.dev.personalassistant.Constants.SELECTED_TAG_KEYS;
import static java.security.AccessController.getContext;

/**
 * Created by saurabh on 4/10/18.
 */

public class
TaggingView implements View.OnClickListener,View.OnLongClickListener {

    GridLayout mtableLayout;
    SharedPreferences mSharedPref;
    TextInputEditText editTag;
    String tagKey;
    Set<String> selectedKeys;

    public TaggingView(GridLayout mtableLayout, SharedPreferences mSharedPref,TextInputEditText editTag, String tagKey){
        this.mtableLayout=mtableLayout;
        this.mSharedPref=mSharedPref;
        this.editTag=editTag;
        this.tagKey=tagKey;
    }



    public void onClick(View view) {
        int mSelectedTagKey= mSharedPref.getInt(SELECTED_TAG_KEY, -1);
        selectedKeys=mSharedPref.getStringSet(SELECTED_TAG_KEYS,new HashSet<String>());
        for(int i=0;i<mtableLayout.getChildCount();i++){
            mtableLayout.getChildAt(i).setBackgroundColor(mtableLayout.getSolidColor());
        }

        TextView tagClicked=(TextView)view;
        //tagClicked.setBackgroundColor(view.getResources().getColor(android.R.color.holo_blue_bright));
        tagClicked.setBackgroundResource(R.drawable.rounded_border_layout_selected);

        if(mSelectedTagKey==tagClicked.getId()){
            //tagClicked.setBackgroundColor(mGridLayout.getSolidColor());
            tagClicked.setBackgroundResource(R.drawable.rounded_border_layout);
            mSelectedTagKey=-1;
            selectedKeys.remove(tagClicked.getId()+"");
        }else{
            mSelectedTagKey=tagClicked.getId();
            editTag.setText(tagClicked.getText());
            selectedKeys.add(mSelectedTagKey+"");
        }
        SharedPreferences.Editor editor=mSharedPref.edit();
        editor.putInt(SELECTED_TAG_KEY,mSelectedTagKey);
        editor.putStringSet(SELECTED_TAG_KEYS,selectedKeys);
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
