package android.dev.personalassistant.utils;

import java.util.Comparator;

/**
 * Created by saurabh on 4/21/18.
 */

public class StringLengthComparator implements Comparator<String> {

    public int compare(String s1,String s2){
        return (s1.length()-s2.length());
    }

}
