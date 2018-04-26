package android.dev.personalassistant.android.dev.personalassistant.adapters;

import android.support.v4.app.Fragment;

/**
 * Created by saurabh on 4/26/18.
 */

public abstract class TabFragment  extends Fragment {
    public abstract Fragment getInstance(int position);
}
