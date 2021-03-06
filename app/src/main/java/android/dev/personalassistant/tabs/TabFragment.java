package android.dev.personalassistant.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by saurabh on 4/26/18.
 */

public abstract class TabFragment  extends Fragment {
    protected int position;

    public abstract Fragment getInstance(int position);

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("pos");
    }

}
